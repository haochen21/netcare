package nsgsw1.netcare.alarm.handle;

import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;

import nsgsw1.netcare.alarm.cache.ExclusionDayCache;
import nsgsw1.netcare.alarm.consumer.SendFaultConsumer;
import nsgsw1.netcare.alarm.util.FaultCircuit;
import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.model.alarm.Fault;
import nsgsw1.netcare.model.alarm.constant.AlarmOperation;
import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.circuit.constant.CircuitBizStatus;
import nsgsw1.netcare.service.AlarmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FaultHandleImpl implements Callable<Fault> {

	private FaultCircuit faultCircuit;

	private Fault fault;

	@Autowired
	private ExclusionDayCache exclusionDayCache;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private SendFaultConsumer sendFaultConsumer;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final static Logger logger = LoggerFactory
			.getLogger(FaultHandleImpl.class);

	public FaultHandleImpl() {

	}

	public FaultCircuit getFaultCircuit() {
		return faultCircuit;
	}

	public void setFaultCircuit(FaultCircuit faultCircuit) {
		this.faultCircuit = faultCircuit;
	}

	@Override
	public Fault call() throws Exception {
		doProcess();
		postProcess();
		return fault;
	}

	public void doProcess() {
		CurrAlarm currAlarm = faultCircuit.getCurrAlarm();
		Circuit circuit = currAlarm
				.getSncCircuits()
				.stream()
				.filter(sncCircuit -> sncCircuit.getNo().equals(
						faultCircuit.getCircuitNo())).findFirst().get();
		if (currAlarm.getOperation() == AlarmOperation.DELETE) {
			boolean isDone = false;
			while (!isDone) {
				try {
					fault = alarmService.clearFault(circuit, currAlarm);
					if (fault != null)
						logger.info("clear fault,circuit is:" + fault.getNo());
					isDone = true;
				} catch (Exception ex) {
					if (ex.toString().contains(
							"OptimisticLockingFailureException")) {
						isDone = false;
						logger.info("clear fault OptimisticLockingFailureException currAlarm uid is is:"
								+ currAlarm.getUid());
					} else {
						isDone = true;
						logger.info("clear fault Exception,uid is: "
								+ currAlarm.getUid(), ex);
					}
				} finally {
					if (!isDone) {
						try {
							Thread.sleep(100);
						} catch (Exception ex) {
							logger.info("clear fault Exception", ex);
						}
					}
				}
			}
		} else if (currAlarm.getOperation() == AlarmOperation.NEW
				|| currAlarm.getOperation() == AlarmOperation.UPDATE) {
			if (circuit.getBizStatus() != null
					&& circuit.getBizStatus() == CircuitBizStatus.BACKUP) {
				logger.info("circuit no:" + circuit.getNo() + ",status is:"
						+ circuit.getBizStatus() + ",is not a fault");
				return;
			}
			boolean exclusionDays = exclusionDayCache.inExclusionDay(circuit,
					currAlarm.getMeCreateTime());
			if (!exclusionDays) {
				boolean isDone = false;
				while (!isDone) {
					try {
						Fault fault = alarmService.createFault(circuit,
								currAlarm);
						isDone = true;
						logger.info(fault.getOperation() + " fault id is:"
								+ fault.getId() + ",no is:" + circuit.getNo());
					} catch (Exception ex) {
						if (ex.toString().contains("DuplicateKeyException")) {
							isDone = false;
							logger.info("DuplicateKeyException circuit no is:"
									+ circuit.getNo());
						} else if (ex.toString().contains(
								"OptimisticLockingFailureException")) {
							isDone = false;
							logger.info("OptimisticLockingFailureException circuit no is:"
									+ circuit.getNo());
						} else if (ex.toString().contains("No value present")) {
							isDone = true;
							logger.info(
									"No value present Exception circuit no is:"
											+ circuit.getNo() + ",uid is:"
											+ currAlarm.getUid(), ex);
						} else {
							isDone = true;
							logger.info("handle fault Exception,uid is: "
									+ currAlarm.getUid(), ex);
						}
					} finally {
						if (!isDone) {
							try {
								Thread.sleep(100);
							} catch (Exception ex) {
								logger.info("handle fault Exception", ex);
							}
						}
					}
				}
			} else {
				logger.info("circuit:" + circuit.getNo()
						+ " is in exclusionDays,meCreateTime is:"
						+ sdf.format(currAlarm.getMeCreateTime()));
			}
		}
	}

	public void postProcess() {
		if (fault != null)
			sendFaultConsumer.addFault(fault);
	}
}
