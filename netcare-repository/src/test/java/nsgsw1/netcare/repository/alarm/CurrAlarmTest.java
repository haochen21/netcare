package nsgsw1.netcare.repository.alarm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.repository.config.MongoConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class CurrAlarmTest {

	@Autowired
	CurrAlarmRepository currAlarmRepository;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Test
	public void testFindAlarmsByEmsAndUpdateTime() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 32);
		calendar.set(Calendar.SECOND, 15);
		Collection<CurrAlarm> alarms = currAlarmRepository
				.findAlarmsByEmsAndUpdateTime("Huawei-sh251",
						sdf.parse("2014-09-26 10:57:01"));
		System.out.println(alarms.size());
	}

}
