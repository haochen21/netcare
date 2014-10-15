package nsgsw1.netcare.repository.circuit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import nsgsw1.netcare.model.circuit.ExclusionDay;
import nsgsw1.netcare.model.circuit.ExclusionDaySet;
import nsgsw1.netcare.repository.config.MongoConfiguration;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class ExclusionDayRepositoryTest {

	@Autowired
	ExclusionDayRepository exclusionDayRepository;

	@Autowired
	CircuitRepository circuitRepository;

	@Autowired
	MongoOperations mongo;

	// @Before
	public void setup() throws Exception {
		mongo.dropCollection("ExclusionDay");
	}

	// @After
	public void teardown() {
		mongo.dropCollection("ExclusionDay");
	}

	@Test
	public void testSave() throws Exception {
		ExclusionDay exclusionDay = new ExclusionDay();
		exclusionDay.setName("∆Ω∞≤º‡øÿ≈‰÷√");

		ExclusionDaySet exclusionDaySet = new ExclusionDaySet();
		exclusionDaySet.setOnlyOnce(true);
		exclusionDaySet.setMon(true);
		exclusionDaySet.setTues(false);
		exclusionDaySet.setWed(false);
		exclusionDaySet.setThur(false);
		exclusionDaySet.setFri(false);
		exclusionDaySet.setSat(true);
		exclusionDaySet.setSun(true);
		exclusionDaySet.setBeginTimeStr("12:00:00");
		exclusionDaySet.setEndTimeStr("23:59:59");
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		exclusionDaySet.setBeginDate(calendar.getTime());
		
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		exclusionDaySet.setEndDate(calendar.getTime());
		exclusionDay.getExclusionDaySets().add(exclusionDaySet);

		exclusionDay = exclusionDayRepository.save(exclusionDay);

		Collection<ObjectId> circuitIds = new ArrayList<>();
		circuitIds.add(new ObjectId("541bc339cde83403610cf5b8"));

		circuitRepository.updateExclusionDay(circuitIds, exclusionDay.getId());
		circuitRepository.updateExclusionDay(circuitIds, exclusionDay.getId());
	}
}
