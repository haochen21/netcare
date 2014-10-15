package nsgsw1.netcare.alarm.cache;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.circuit.ExclusionDay;
import nsgsw1.netcare.model.circuit.ExclusionDaySet;
import nsgsw1.netcare.service.CircuitService;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExclusionDayCache {

	private Map<ObjectId, ExclusionDay> exclusionDayMap = new ConcurrentHashMap<>();

	@Autowired
	private CircuitService circuitService;

	private final static Logger logger = LoggerFactory
			.getLogger(AlarmKnowledgeCache.class);

	public ExclusionDayCache() {

	}

	@Scheduled(fixedDelay = 10800000)
	public void start() {
		exclusionDayMap.clear();
		Collection<ExclusionDay> exclusionDays = circuitService
				.findAllExclusionDay();
		Map<ObjectId, ExclusionDay> tempMap = exclusionDays.stream().collect(
				Collectors.toMap(ExclusionDay::getId,
						exclusionDay -> exclusionDay));
		exclusionDayMap.putAll(tempMap);
		logger.info("exclusionDay size is:" + exclusionDays.size());
	}

	private ExclusionDay findExclusionDayById(ObjectId id) {
		return exclusionDayMap.get(id);
	}

	public boolean inExclusionDay(Circuit circuit, Date meUpdateTime) {
		Instant meInstant = meUpdateTime.toInstant();
		for (ObjectId objectId : circuit.getExclusionDayIds()) {
			ExclusionDay exclusionDay = findExclusionDayById(objectId);
			if (exclusionDay != null) {
				for (ExclusionDaySet exclusionDaySet : exclusionDay
						.getExclusionDaySets()) {
					// 一次性时间设置
					if (exclusionDaySet.isOnlyOnce()) {
						Instant beginInstant = exclusionDaySet.getBeginDate()
								.toInstant();
						Instant endInstant = exclusionDaySet.getEndDate()
								.toInstant();
						if (meInstant.isAfter(beginInstant)
								&& meInstant.isBefore(endInstant)) {
							return true;
						}
					} else {
						try {
							DayOfWeek dayOfWeek = LocalDate.now()
									.getDayOfWeek();
							if (inDayOfWeek(dayOfWeek, exclusionDaySet)) {
								DateTimeFormatter formatter = DateTimeFormatter
										.ofPattern("HH:mm:ss");
								LocalTime beginLocalTime = LocalTime.parse(
										exclusionDaySet.getBeginTimeStr(),
										formatter);
								LocalTime endLocalTime = LocalTime.parse(
										exclusionDaySet.getEndTimeStr(),
										formatter);
								SimpleDateFormat sdf = new SimpleDateFormat(
										"HH:mm:ss");
								LocalTime meLocalTime = LocalTime.parse(
										sdf.format(meUpdateTime), formatter);
								if (meLocalTime.isAfter(beginLocalTime)
										&& meLocalTime.isBefore(endLocalTime))
									return true;
							}
						} catch (Exception ex) {
							logger.info("parse time error!", ex);
						}
					}
				}
			}
		}
		return false;
	}

	private boolean inDayOfWeek(DayOfWeek dayOfWeek,
			ExclusionDaySet exclusionDaySet) {
		if (dayOfWeek == DayOfWeek.SUNDAY && exclusionDaySet.isSun()) {
			return true;
		} else if (dayOfWeek == DayOfWeek.MONDAY && exclusionDaySet.isMon()) {
			return true;
		} else if (dayOfWeek == DayOfWeek.TUESDAY && exclusionDaySet.isTues()) {
			return true;
		} else if (dayOfWeek == DayOfWeek.WEDNESDAY && exclusionDaySet.isWed()) {
			return true;
		} else if (dayOfWeek == DayOfWeek.THURSDAY && exclusionDaySet.isThur()) {
			return true;
		} else if (dayOfWeek == DayOfWeek.FRIDAY && exclusionDaySet.isFri()) {
			return true;
		} else if (dayOfWeek == DayOfWeek.SATURDAY && exclusionDaySet.isSat()) {
			return true;
		} else
			return false;
	}
}
