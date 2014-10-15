package nsgsw1.netcare.model.circuit;

import java.io.Serializable;
import java.util.Date;

public class ExclusionDaySet implements Serializable {

	private String name;

	// true : ʹ�ÿ�ʼ���ڡ���������
	// false : ʹ�ÿ�ʼʱ�䡢���ʱ�估���ڼ�
	private boolean onlyOnce;

	// ��ʼʱ�� hh:mm:ss
	private String beginTimeStr;

	// ����ʱ�� hh:mm:ss
	private String endTimeStr;

	// ����һ
	private boolean mon;

	// ���ڶ�
	private boolean tues;

	// ������
	private boolean wed;

	// ������
	private boolean thur;

	// ������
	private boolean fri;

	// ������
	private boolean sat;

	// ������
	private boolean sun;

	// ��ʼ����
	private Date beginDate;

	// ��������
	private Date endDate;

	private static final long serialVersionUID = -7567923186479264074L;

	public ExclusionDaySet() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnlyOnce() {
		return onlyOnce;
	}

	public void setOnlyOnce(boolean onlyOnce) {
		this.onlyOnce = onlyOnce;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public boolean isMon() {
		return mon;
	}

	public void setMon(boolean mon) {
		this.mon = mon;
	}

	public boolean isTues() {
		return tues;
	}

	public void setTues(boolean tues) {
		this.tues = tues;
	}

	public boolean isWed() {
		return wed;
	}

	public void setWed(boolean wed) {
		this.wed = wed;
	}

	public boolean isThur() {
		return thur;
	}

	public void setThur(boolean thur) {
		this.thur = thur;
	}

	public boolean isFri() {
		return fri;
	}

	public void setFri(boolean fri) {
		this.fri = fri;
	}

	public boolean isSat() {
		return sat;
	}

	public void setSat(boolean sat) {
		this.sat = sat;
	}

	public boolean isSun() {
		return sun;
	}

	public void setSun(boolean sun) {
		this.sun = sun;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "ExclusionDaysSet [name=" + name + ", onlyOnce=" + onlyOnce
				+ "]";
	}
}
