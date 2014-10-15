package nsgsw1.netcare.model.res;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.Indexed;

public class Shelf implements Serializable{

	@Indexed
	private String dn;
	
	private String no;

	private String userLabel;	

	private String fullCnName;
	
	private static final long serialVersionUID = 2383828417878038591L;

	public Shelf(){
		
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public String getFullCnName() {
		return fullCnName;
	}

	public void setFullCnName(String fullCnName) {
		this.fullCnName = fullCnName;
	}
}
