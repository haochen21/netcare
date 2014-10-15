package nsgsw1.netcare.shres.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CIRCUITTEXTROUTE")
@NamedQueries({ @NamedQuery(name = "ResCircuitTextRoute.findByCircuitId", query = "SELECT tr FROM ResCircuitTextRoute tr where tr.circuitId =:circuitId") })
public class ResCircuitTextRoute implements Serializable {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "TEXTROUTE")
	private String textRoute;

	@Column(name = "CIRCUITID")
	private Long circuitId;

	private static final long serialVersionUID = -7289376116926014483L;

	public ResCircuitTextRoute() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTextRoute() {
		return textRoute;
	}

	public void setTextRoute(String textRoute) {
		this.textRoute = textRoute;
	}

	public Long getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(Long circuitId) {
		this.circuitId = circuitId;
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
		ResCircuitTextRoute other = (ResCircuitTextRoute) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResCircuitTextRoute [id=" + id + ", circuitId=" + circuitId
				+ "]";
	}
}
