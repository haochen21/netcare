package nsgsw1.netcare.shres.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaGenericRepository<T extends Serializable> implements
		GenericRepository<T> {

	@PersistenceContext
	protected EntityManager em;

	private Class<T> clazz;

	public JpaGenericRepository(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public T save(T entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(T entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	@Override
	public T findOne(Long id) {
		return em.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return em.createQuery("from " + clazz.getName()).getResultList();
	}

}
