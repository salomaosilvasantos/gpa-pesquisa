package br.com.ufc.quixada.npi.gpa.service;

import java.util.List;
import java.util.Map;

import br.com.ufc.quixada.npi.gpa.repository.QueryType;

public interface GenericService<T> {
	
	 abstract void save(T entity);

	 abstract void update(T entity);

	 abstract T find(Class<T> entityClass, Object id);

	 abstract List<T> find(Class<T> entityClass);

	 abstract void delete(T entity);
	
	 abstract List<T> find(QueryType type, String query,
			Map<String, Object> namedParams);
}
