package nsgsw1.netcare.model.circuit;

import java.io.Serializable;
import java.util.Date;

public class ExclusionDaySet implements Serializable {

	private String name;

	// true : 使用开始日期、结束日期
	// false : 使用开始时间、结果时间及星期几
	private boolean onlyOnce;

	// 开始时间 hh:mm:ss
	private String beginTimeStr;

	// 结束时间 hh:mm:ss
	private String endTimeStr;

	// 星期一
	private boolean mon;

	// 星期二
	private boolean tues;

	// 星期三
	private boolean wed;

	// 星期四
	private boolean thur;

	// 星期五
	private boolean fri;

	// 星期六
	private boolean sat;

	// 星期天
	private boolean sun;

	// 开始日期
	private Date beginDate;

	// 结束日期
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
