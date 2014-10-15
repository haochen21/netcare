package nsgsw1.netcare.shres.sync;

import java.util.Collection;

import nsgsw1.netcare.model.alarm.AlarmKnowledge;
import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.service.AlarmService;
import nsgsw1.netcare.shres.model.ResAlarmKnowledge;
import nsgsw1.netcare.shres.service.ResAlarmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncAlarmKnowledge {

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private ResAlarmService resAlarmService;

	public void sync() {
		Collection<ResAlarmKnowledge> resAlarmKnowledges = resAlarmService
				.findAllResAlarmKnowledge();
		for (ResAlarmKnowledge resAlarmKnowledge : resAlarmKnowledges) {
			AlarmKnowledge alarmKnowledge = new AlarmKnowledge();
			alarmKnowledge.setName(resAlarmKnowledge.getName());
			alarmKnowledge.setCustomizeName(resAlarmKnowledge
					.getCustomizeName());
			alarmKnowledge.setDescription(resAlarmKnowledge.getDescription());
			alarmKnowledge.setProblemCause(resAlarmKnowledge.getProblemCause());
			if (resAlarmKnowledge.getVendor() != null)
				alarmKnowledge.setVendor(ResClassConvert.INSTANCE
						.convertVendor(resAlarmKnowledge.getVendor()));
			alarmKnowledge.setBusiness(resAlarmKnowledge.getBusiness());
			if (resAlarmKnowledge.getCategory() != null)
				alarmKnowledge.setCategory(AlarmCategory
						.getFieldTypeByOrdinal(resAlarmKnowledge.getCategory()
								.ordinal()));
			alarmKnowledge.setNetAlarm(resAlarmKnowledge.getNetAlarm());
			alarmService.saveAlarmKnowledge(alarmKnowledge);
		}
	}
}
