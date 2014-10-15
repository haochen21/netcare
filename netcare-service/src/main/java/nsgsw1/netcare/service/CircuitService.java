package nsgsw1.netcare.service;

import java.util.Collection;
import java.util.List;

import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.circuit.CircuitTextRoute;
import nsgsw1.netcare.model.circuit.ExclusionDay;
import nsgsw1.netcare.model.circuit.QueryCircuitModel;
import nsgsw1.netcare.model.circuit.Snc;

import org.bson.types.ObjectId;

public interface CircuitService {

	Circuit findCircuitByNo(String no);

	Circuit saveCircuit(Circuit circuit);

	List<Circuit> findCircuitByIds(Collection<ObjectId> ids);	

	QueryCircuitModel findSncModelByDn(AlarmObjectType objectType, String dn);

	QueryCircuitModel findChannelModelByDn(AlarmObjectType objectType, String dn);

	CircuitTextRoute findTextRouteByCircuitId(ObjectId circuitId);

	CircuitTextRoute saveCircuitTextRoute(CircuitTextRoute circuitTextRoute);

	void deleteSncByCircuitId(ObjectId circuitId);

	List<Snc> saveSncs(List<Snc> sncs);

	Collection<ExclusionDay> findAllExclusionDay();
}
