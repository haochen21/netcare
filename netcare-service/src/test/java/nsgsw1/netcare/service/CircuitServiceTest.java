package nsgsw1.netcare.service;

import static junit.framework.TestCase.assertEquals;
import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.circuit.QueryCircuitModel;
import nsgsw1.netcare.repository.config.MongoConfiguration;
import nsgsw1.netcare.service.config.ServiceConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class, ServiceConfig.class })
public class CircuitServiceTest {

	@Autowired
	CircuitService circuitService;

	public void testSncQueryCircuitModel() throws Exception {
		QueryCircuitModel model = circuitService
				.findSncModelByDn(
						AlarmObjectType.CTP,
						"EMS~Huawei/U2000XHP-3@ManagedElement~3145869@PTP~/rack=1/shelf=1/slot=3/domain=sdh/port=1@CTP~/sts3c_au4-j=1");
		assertEquals(1, model.getCircuits().size());
		assertEquals("EMS~Huawei/U2000XHP-3@ManagedElement~3145869", model
				.getMe().getDn());
	}

	@Test
	public void testChannelQueryCircuitModel() throws Exception {
		QueryCircuitModel model = circuitService
				.findChannelModelByDn(
						AlarmObjectType.CTP,
						"EMS~Huawei/U2000XHP-3@ManagedElement~3145738@PTP~/rack=1/shelf=1/slot=36/domain=sdh/port=1@CTP~/sts3c_au4-j=26");
		assertEquals(1, model.getCircuits().size());
		assertEquals("EMS~Huawei/U2000XHP-3@ManagedElement~3145738", model
				.getMe().getDn());
	}
}
