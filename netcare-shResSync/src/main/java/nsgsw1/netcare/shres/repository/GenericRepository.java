package nsgsw1.netcare.shres.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T extends Serializable> {

	T save(T entity);

	T update(T entity);

	T findOne(Long id);

	List<T> findAll();

	void delete(T entity);
}