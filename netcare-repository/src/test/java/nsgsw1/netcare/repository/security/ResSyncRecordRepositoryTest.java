package nsgsw1.netcare.repository.security;

import static junit.framework.TestCase.assertEquals;

import java.util.Calendar;

import nsgsw1.netcare.model.security.ResSyncRecord;
import nsgsw1.netcare.repository.config.MongoConfiguration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class ResSyncRecordRepositoryTest {

	@Autowired
	private ResSyncRecordRepository resSyncRecordRepository;
	
	@Autowired
	MongoOperations mongo;	
	
	@Before
	public void setup() throws Exception {
		mongo.dropCollection("ResourceSyncRecord");
	}

	@After
	public void teardown() {
		mongo.dropCollection("ResourceSyncRecord");
	}
	
	@Test
	public void testSaveUpdate() throws Exception{
		ResSyncRecord record = new ResSyncRecord();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		record.setResType(ResSyncRecord.Type.CUSTOMER);
		record.setLastSyncTime(calendar.getTime());
		record = resSyncRecordRepository.save(record);
		
		calendar.add(Calendar.HOUR_OF_DAY, 1);
	    record.setLastSyncTime(calendar.getTime());
	    resSyncRecordRepository.save(record);
	     
	    ResSyncRecord updateRecord = resSyncRecordRepository.findByType(ResSyncRecord.Type.CUSTOMER);
	    assertEquals(updateRecord.getLastSyncTime(),record.getLastSyncTime());
	}
}
