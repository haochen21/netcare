package nsgsw1.netcare.model.circuit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.model.res.Ctp;
import nsgsw1.netcare.model.res.Region;
import nsgsw1.netcare.model.res.constant.SncType;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Snc")
public class Snc implements Serializable {

	@Id
	private ObjectId id;

	@Indexed
	private ObjectId circuitId;

	private SncType type;

	private Date updateTime;

	private Ctp aCtp;

	private Ctp zCtp;

	private Region sncRegion;

	Collection<Channel> channels = new ArrayList<>();

	@Version
	private Long version;

	private static final long serialVersionUID = -6048511005265536337L;

	public Snc() {

	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(ObjectId circuitId) {
		this.circuitId = circuitId;
	}

	public SncType getType() {
		return type;
	}

	public void setType(SncType type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Ctp getaCtp() {
		return aCtp;
	}

	public void setaCtp(Ctp aCtp) {
		this.aCtp = aCtp;
	}

	public Ctp getzCtp() {
		return zCtp;
	}

	public void setzCtp(Ctp zCtp) {
		this.zCtp = zCtp;
	}

	public Region getSncRegion() {
		return sncRegion;
	}

	public void setSncRegion(Region sncRegion) {
		this.sncRegion = sncRegion;
	}

	public Collection<Channel> getChannels() {
		return channels;
	}

	public void setChannels(Collection<Channel> channels) {
		this.channels = channels;
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
		result = prime * result
				+ ((circuitId == null) ? 0 : circuitId.hashCode());
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
		Snc other = (Snc) obj;
		if (circuitId == null) {
			if (other.circuitId != null)
				return false;
		} else if (!circuitId.equals(other.circuitId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Snc [circuitId=" + circuitId + ", type=" + type + ", aCtp="
				+ aCtp + ", zCtp=" + zCtp + "]";
	}

}
