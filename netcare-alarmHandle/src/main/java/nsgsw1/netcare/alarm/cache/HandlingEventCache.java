package nsgsw1.netcare.alarm.cache;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HandlingEventCache {

	private Set<String> alarmEventDnCache;

	private Set<String> faultCircuitNoCache;

	public HandlingEventCache() {

	}

	public void start() {
		alarmEventDnCache = Collections.synchronizedSet(new HashSet<String>());
		faultCircuitNoCache = Collections
				.synchronizedSet(new HashSet<String>());
	}

	public void addAlarmEvent(String dn) {
		alarmEventDnCache.add(dn);
	}

	public void removeAlarmEvent(String dn) {
		alarmEventDnCache.remove(dn);
	}

	public boolean isExistInAlarmEvent(String dn) {
		return alarmEventDnCache.contains(dn);
	}

	public int getAlarmEventSize() {
		return alarmEventDnCache.size();
	}

	public void addFaultCircuitNo(String circuitNo) {
		faultCircuitNoCache.add(circuitNo);
	}

	public void removeFaultCircuitNo(String circuitNo) {
		faultCircuitNoCache.remove(circuitNo);
	}

	public boolean isExistInFaultCircuit(String circuitNo) {
		return faultCircuitNoCache.contains(circuitNo);
	}

	public int getFaultCircuitNoSize() {
		return faultCircuitNoCache.size();
	}
}
