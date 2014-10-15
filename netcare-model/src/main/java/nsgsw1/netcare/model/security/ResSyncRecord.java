package nsgsw1.netcare.model.security;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ResSyncRecord")
public class ResSyncRecord implements Serializable {

	public enum Type {
		CUSTOMER, CIRCUIT
	}

	@Id
	private ObjectId id;

	private ResSyncRecord.Type resType;

	private Date lastSyncTime;

	private static final long serialVersionUID = 1184734572471606251L;

	public ResSyncRecord() {

	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public ResSyncRecord.Type getResType() {
		return resType;
	}

	public void setResType(ResSyncRecord.Type resType) {
		this.resType = resType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ResSyncRecord other = (ResSyncRecord) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResSyncRecord [id=" + id + ", resType=" + resType
				+ ", lastSyncTime=" + lastSyncTime + "]";
	}

}
