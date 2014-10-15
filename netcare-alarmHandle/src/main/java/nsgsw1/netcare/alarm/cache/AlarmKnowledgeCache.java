package nsgsw1.netcare.alarm.cache;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import nsgsw1.netcare.model.alarm.AlarmKnowledge;
import nsgsw1.netcare.service.AlarmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlarmKnowledgeCache {

	// key:vendorName~alarmName(sdh) || deviceType~alarmName
	private Map<String, AlarmKnowledge> alarmKnowledgeMap = new ConcurrentHashMap<>();

	@Autowired
	private AlarmService alarmService;

	private final static Logger logger = LoggerFactory
			.getLogger(AlarmKnowledgeCache.class);

	public AlarmKnowledgeCache() {

	}

	@Scheduled(fixedDelay = 10800000)
	public void start() {
		alarmKnowledgeMap.clear();
		Collection<AlarmKnowledge> alarmKnowledges = alarmService
				.findAllAlarmKnowledges();
		Map<String, AlarmKnowledge> tempMap = alarmKnowledges.stream().collect(
				Collectors.toMap(AlarmKnowledge::getKey,
						alarmKnowledge -> alarmKnowledge, (v1, v2) -> v1));
		alarmKnowledgeMap.putAll(tempMap);
		logger.info("alarmKnowledge size is:" + alarmKnowledgeMap.size());
	}

	public AlarmKnowledge getAlarmKnowledge(String key) {
		if (key.equals(""))
			return null;
		else if (alarmKnowledgeMap.containsKey(key))
			return alarmKnowledgeMap.get(key);
		else
			return null;
	}

	public void removeAlarmKnowledge(AlarmKnowledge alarmKnowledge) {
		String key = alarmKnowledge.getKey();
		if (alarmKnowledgeMap.containsKey(key))
			alarmKnowledgeMap.remove(key);
	}
}
