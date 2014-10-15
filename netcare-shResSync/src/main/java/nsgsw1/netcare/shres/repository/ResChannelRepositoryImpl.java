package nsgsw1.netcare.shres.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import nsgsw1.netcare.shres.model.ResChannel;

@Repository
public class ResChannelRepositoryImpl extends JpaGenericRepository<ResChannel>
		implements ResChannelRepository {

	public ResChannelRepositoryImpl() {
		super(ResChannel.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ResChannel> findBySncId(Long sncId) {
		Collection<ResChannel> channels = new ArrayList<ResChannel>();
		String sql = " select distinct ch.* from channel ch,routechannel rc,route ro,subnetworkconnection snc where CH.ID = RC.CHANNELID and RO.ID = RC.ROUTEID and RO.SNCID = "
				+ sncId;		
		Query query = em.createNativeQuery(sql, ResChannel.class);
		List<ResChannel> dbChannels = query.getResultList();
		if (dbChannels != null && dbChannels.size() > 0) {
			channels.addAll(dbChannels);
		}
		return channels;
	}

}
