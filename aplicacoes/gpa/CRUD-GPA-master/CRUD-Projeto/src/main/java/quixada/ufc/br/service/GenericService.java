package quixada.ufc.br.service;

import java.util.List;

public interface GenericService<T> {
	
	public abstract void save(T entity);

	public abstract void update(T entity);

	public abstract T find(Class<T> entityClass, Object id);

	public abstract List<T> find(Class<T> entityClass);

	public abstract void delete(T entity);
}
