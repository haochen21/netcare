package nsgsw1.netcare.alarm.util;

import nsgsw1.netcare.model.alarm.CurrAlarm;

public class FaultCircuit {

	private String circuitNo;
	
	private CurrAlarm currAlarm;
	
	public FaultCircuit(){
	
	}

	public String getCircuitNo() {
		return circuitNo;
	}

	public void setCircuitNo(String circuitNo) {
		this.circuitNo = circuitNo;
	}

	public CurrAlarm getCurrAlarm() {
		return currAlarm;
	}

	public void setCurrAlarm(CurrAlarm currAlarm) {
		this.currAlarm = currAlarm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((circuitNo == null) ? 0 : circuitNo.hashCode());
		result = prime * result
				+ ((currAlarm == null) ? 0 : currAlarm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FaultCircuit other = (FaultCircuit) obj;
		if (circuitNo == null) {
			if (other.circuitNo != null)
				return false;
		} else if (!circuitNo.equals(other.circuitNo))
			return false;
		if (currAlarm == null) {
			if (other.currAlarm != null)
				return false;
		} else if (!currAlarm.equals(other.currAlarm))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FaultCircuit [circuitNo=" + circuitNo + ", currAlarm="
				+ currAlarm + "]";
	}
}
