package nsgsw1.netcare.repository.circuit;

import java.util.List;

import nsgsw1.netcare.model.circuit.Snc;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SncRepository extends MongoRepository<Snc, ObjectId>,
		SncRepositoryCustom {

	@Query("{$or:[{'aCtp.me.dn':?0},{'zCtp.me.dn':?0}]}")
	List<Snc> findByMeDn(String meDn);

	@Query("{$or:[{'aCtp.shelf.dn':?0},{'zCtp.shelf.dn':?0}]}")
	List<Snc> findByShelfDn(String shelfDn);

	@Query("{$or:[{'aCtp.slot.dn':?0},{'zCtp.slot.dn':?0}]}")
	List<Snc> findBySlotDn(String slotDn);

	@Query("{$or:[{'aCtp.card.dn':?0},{'zCtp.card.dn':?0}]}")
	List<Snc> findByCardDn(String cardDn);

	@Query("{$or:[{'aCtp.port.dn':?0},{'zCtp.port.dn':?0}]}")
	List<Snc> findByPortDn(String meDn);

	@Query("{$or:[{'aCtp.dn':?0},{'zCtp.dn':?0}]}")
	List<Snc> findByCtpDn(String meDn);

	@Query("{$or:[{'channels.channelACtp.me.dn':?0},{'channels.channelZCtp.me.dn':?0}]}")
	List<Snc> findByChannelMeDn(String meDn);

	@Query("{$or:[{'channels.channelACtp.shelf.dn':?0},{'channels.channelZCtp.shelf.dn':?0}]}")
	List<Snc> findByChannelShelfDn(String shelfDn);

	@Query("{$or:[{'channels.channelACtp.slot.dn':?0},{'channels.channelZCtp.slot.dn':?0}]}")
	List<Snc> findByChannelSlotDn(String slotDn);

	@Query("{$or:[{'channels.channelACtp.card.dn':?0},{'channels.channelZCtp.card.dn':?0}]}")
	List<Snc> findByChannelCardDn(String cardDn);

	@Query("{$or:[{'channels.channelACtp.port.dn':?0},{'channels.channelZCtp.port.dn':?0}]}")
	List<Snc> findByChannelPortDn(String portDn);

	@Query("{$or:[{'channels.channelACtp.dn':?0},{'channels.channelZCtp.dn':?0}]}")
	List<Snc> findByChannelCtpDn(String ctpDn);
}
