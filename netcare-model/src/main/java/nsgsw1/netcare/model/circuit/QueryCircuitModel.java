package nsgsw1.netcare.model.circuit;

import java.io.Serializable;
import java.util.List;

import nsgsw1.netcare.model.res.Card;
import nsgsw1.netcare.model.res.Ctp;
import nsgsw1.netcare.model.res.Me;
import nsgsw1.netcare.model.res.Port;
import nsgsw1.netcare.model.res.Shelf;
import nsgsw1.netcare.model.res.Slot;

public class QueryCircuitModel implements Serializable {

	private Me me;

	private Shelf shelf;

	private Slot slot;

	private Card card;

	private Port port;

	private Ctp ctp;

	List<Circuit> circuits;

	private static final long serialVersionUID = -5367680517189417319L;

	public QueryCircuitModel() {

	}

	public Me getMe() {
		return me;
	}

	public void setMe(Me me) {
		this.me = me;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	public Ctp getCtp() {
		return ctp;
	}

	public void setCtp(Ctp ctp) {
		this.ctp = ctp;
	}

	public List<Circuit> getCircuits() {
		return circuits;
	}

	public void setCircuits(List<Circuit> circuits) {
		this.circuits = circuits;
	}

}
