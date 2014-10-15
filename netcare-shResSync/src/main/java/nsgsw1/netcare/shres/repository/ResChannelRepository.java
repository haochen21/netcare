package nsgsw1.netcare.shres.repository;

import java.util.Collection;

import nsgsw1.netcare.shres.model.ResChannel;

public interface ResChannelRepository extends GenericRepository<ResChannel> {

	Collection<ResChannel> findBySncId(Long sncId);
}
