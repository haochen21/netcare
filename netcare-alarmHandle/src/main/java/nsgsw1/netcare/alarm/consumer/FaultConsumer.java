package nsgsw1.netcare.alarm.consumer;

import java.util.Stack;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import nsgsw1.netcare.alarm.cache.HandlingEventCache;
import nsgsw1.netcare.alarm.handle.FaultHandleImpl;
import nsgsw1.netcare.alarm.util.FaultCircuit;
import nsgsw1.netcare.alarm.util.GlobalContext;
import nsgsw1.netcare.model.alarm.Fault;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class FaultConsumer extends Thread {

	private int sleepTime;

	private int handleTime;

	private BlockingDeque<FaultCircuit> faultQueue;

	private HandlingEventCache handlingEventCache;

	private Stack<FaultCircuit> faultStack = new Stack<>();

	private final static Logger logger = LoggerFactory
			.getLogger(FaultConsumer.class);

	public FaultConsumer() {

	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public int getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(int handleTime) {
		this.handleTime = handleTime;
	}

	public BlockingDeque<FaultCircuit> getFaultQueue() {
		return faultQueue;
	}

	public void setFaultQueue(BlockingDeque<FaultCircuit> faultQueue) {
		this.faultQueue = faultQueue;
	}

	public HandlingEventCache getHandlingEventCache() {
		return handlingEventCache;
	}

	public void setHandlingEventCache(HandlingEventCache handlingEventCache) {
		this.handlingEventCache = handlingEventCache;
	}

	@Override
	public void run() {
		while (!super.isInterrupted()) {
			FaultCircuit faultCircuit = null;
			FaultHandleImpl handle = null;
			FutureTask<Fault> task = null;
			try {
				faultCircuit = faultQueue.pollFirst();
				if (faultCircuit != null) {
					if (handlingEventCache.isExistInFaultCircuit(faultCircuit
							.getCircuitNo())) {
						logger.info(this.getName()
								+ ",faultCircuit is handling: "
								+ faultCircuit.toString());
						faultStack.push(faultCircuit);
						continue;
					}
					pollStack();
					handlingEventCache.addFaultCircuitNo(faultCircuit
							.getCircuitNo());

					StopWatch stopWatch = new StopWatch();
					stopWatch.start();
					handle = (FaultHandleImpl) GlobalContext.getInstance()
							.getApplicationContext().getBean("faultHandleImpl");
					handle.setFaultCircuit(faultCircuit);
					task = new FutureTask<>(handle);
					Thread thread = new Thread(task);
					thread.start();
					task.get(handleTime, TimeUnit.SECONDS);
					stopWatch.stop();
					long time = stopWatch.getTotalTimeMillis();
					logger.info(this.getName() + ","
							+ " handle fault,time is: " + time + "ms");
					handlingEventCache.removeFaultCircuitNo(faultCircuit
							.getCircuitNo());

				} else {
					pollStack();
					Thread.sleep(sleepTime);
				}
			} catch (Exception ex) {
				logger.info("handle fault Exception,uid is: ", ex);
				handlingEventCache.removeFaultCircuitNo(faultCircuit
						.getCircuitNo());
			} finally {
				faultCircuit = null;
				handle = null;
			}
		}
	}

	private void pollStack() {
		while (!faultStack.isEmpty()) {
			try {
				faultQueue.putFirst(faultStack.pop());
			} catch (Exception ex) {
				logger.info("put stack error!", ex);
			}
		}
	}

	@Override
	public synchronized void start() {
		logger.info("Start consume fault,name is:" + this.getName());
		super.start();
	}

	@Override
	public void interrupt() {
		logger.info("Interrupt consume fault,name is:" + this.getName());
		super.interrupt();
	}
}
