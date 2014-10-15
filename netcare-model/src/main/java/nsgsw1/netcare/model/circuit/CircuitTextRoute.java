package nsgsw1.netcare.model.circuit;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CircuitTextRoute")
public class CircuitTextRoute implements Serializable {

	@Id
	private ObjectId id;

	// µçÂ·±àºÅ
	private String no;

	@Indexed(unique = true)
	private ObjectId circuitId;

	private Date updateTime;

	private String textRoute;

	@Version
	private Long version;

	private static final long serialVersionUID = -4678588482037663728L;

	public CircuitTextRoute() {

	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public ObjectId getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(ObjectId circuitId) {
		this.circuitId = circuitId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTextRoute() {
		return textRoute;
	}

	public void setTextRoute(String textRoute) {
		this.textRoute = textRoute;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((no == null) ? 0 : no.hashCode());
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
		CircuitTextRoute other = (CircuitTextRoute) obj;
		if (no == null) {
			if (other.no != null)
				return false;
		} else if (!no.equals(other.no))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CircuitTextRoute [no=" + no + "]";
	}
}
