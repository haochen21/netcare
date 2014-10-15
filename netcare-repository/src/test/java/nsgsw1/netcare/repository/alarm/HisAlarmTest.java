package nsgsw1.netcare.repository.alarm;

import java.util.ArrayList;
import java.util.Collection;

import nsgsw1.netcare.repository.config.MongoConfiguration;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class HisAlarmTest {

	@Autowired
	HisAlarmRepository hisAlarmRepository;

	@Test
	public void testFindByIds() throws Exception {
		Collection<ObjectId> ids = new ArrayList<>();
//		ids.add(new ObjectId("5425f3c8539a7a83db84dff1"));
//		ids.add(new ObjectId("5425f38e539a7a83db84dfca"));
//		ids.add(new ObjectId("5425f389539a7a83db84dfc4"));
//		ids.add(new ObjectId("5425f3ab539a7a83db84dfe1"));
//		ids.add(new ObjectId("5425f3c9539a7a83db84dff2"));
//		ids.add(new ObjectId(""));
//		ids.add(new ObjectId("5425f3de539a7a83db84e01d"));
//		ids.add(new ObjectId("5425f3e5539a7a83db84e041"));
//		ids.add(new ObjectId("5425f3e6539a7a83db84e04c"));
//		ids.add(new ObjectId("5425f3e8539a7a83db84e04e"));
//		hisAlarmRepository.findByIds(ids);
	}
}
