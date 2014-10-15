package nsgsw1.netcare.model.circuit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ExclusionDay")
public class ExclusionDay implements Serializable {

	@Id
	private ObjectId id;

	private String name;

	private Collection<ExclusionDaySet> exclusionDaySets = new ArrayList<>();

	@Version
	private Long version;

	private static final long serialVersionUID = 1321978879530938438L;

	public ExclusionDay() {

	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ExclusionDaySet> getExclusionDaySets() {
		return exclusionDaySets;
	}

	public void setExclusionDaySets(Collection<ExclusionDaySet> exclusionDaySets) {
		this.exclusionDaySets = exclusionDaySets;
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
		ExclusionDay other = (ExclusionDay) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CircuitExclusionDays [name=" + name + "]";
	}
}
