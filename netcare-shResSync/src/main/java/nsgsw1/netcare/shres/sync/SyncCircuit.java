package nsgsw1.netcare.shres.sync;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.model.security.ResSyncRecord;
import nsgsw1.netcare.service.SecurityService;
import nsgsw1.netcare.shres.service.ResCircuitService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class SyncCircuit implements ApplicationContextAware {

	@Autowired
	private ResCircuitService resCircuitService;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private SecurityService securityService;

	private ResSyncRecord resSyncRecord;

	private ApplicationContext applicationContext;

	private final static Logger logger = LoggerFactory
			.getLogger(SyncCircuit.class);

	public SyncCircuit() {

	}

	@Scheduled(cron="0 25 13 * * *")
	public void sync() {
		// 获取资源上次最后更新时间
		getLastUpdateTime();
		Collection<String> circuitNos = resCircuitService
				.findCircuitNoByLastUpdateTime(resSyncRecord.getLastSyncTime());		
		//Collection<String> circuitNos = new ArrayList<>();			
		//circuitNos.add("01T020654");
		logger.info("get update circuit size is:" + circuitNos.size());	
		for (String circuitNo : circuitNos) {
			SyncSingleCircuit syncSingleCircuit = applicationContext
					.getBean(SyncSingleCircuit.class);
			syncSingleCircuit.setCircuitNo(circuitNo);
			taskExecutor.execute(syncSingleCircuit);
		}
		updateLastUpdateTime();
	}

	private void getLastUpdateTime() {
		resSyncRecord = securityService
				.findResSyncRecordByType(ResSyncRecord.Type.CIRCUIT);
		if (resSyncRecord == null) {
			resSyncRecord = new ResSyncRecord();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 1990);
			resSyncRecord.setLastSyncTime(calendar.getTime());
			resSyncRecord.setResType(ResSyncRecord.Type.CIRCUIT);
		}
	}

	private void updateLastUpdateTime() {
		Date lastUpdateTime = resCircuitService.findLastUpdateTime();
		resSyncRecord.setLastSyncTime(lastUpdateTime);
		securityService.saveResSyncRecord(resSyncRecord);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
