package nsgsw1.netcare.shres.repository;

import nsgsw1.netcare.shres.model.ResAlarmKnowledge;

import org.springframework.stereotype.Repository;

@Repository
public class ResAlarmKnowledgeRepositoryImpl extends
		JpaGenericRepository<ResAlarmKnowledge> implements
		ResAlarmKnowledgeRepository {

	public ResAlarmKnowledgeRepositoryImpl() {
		super(ResAlarmKnowledge.class);
	}

}
