package nsgsw1.netcare.repository.circuit;

import static junit.framework.TestCase.assertEquals;

import java.util.Collection;
import java.util.HashSet;

import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.repository.config.MongoConfiguration;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class CircuitRepositoryTest {

	@Autowired
	CircuitRepository circuitRepository;

	public void testFindByIds() throws Exception {
		Collection<ObjectId> ids = new HashSet<>();
		ids.add(new ObjectId("541bc339cde83403610cf5b8"));
		Collection<Circuit> circuits = circuitRepository.findByIds(ids);
		assertEquals(1, circuits.size());
	}
	
	@Test
	public void testFindByIdsStatus() throws Exception {
		Collection<ObjectId> ids = new HashSet<>();
		ids.add(new ObjectId("542233b3539ab9843390a838"));
		Collection<Circuit> circuits = circuitRepository.findByIdsAndStatus(ids,true);
		assertEquals(0, circuits.size());
	}
}
