package br.com.ufc.quixada.npi.gpa.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;



public interface GenericRepository<T> {

	 abstract void setEntityManager(EntityManager em);

	 abstract void save(T entity);
	
	 abstract void update(T entity);

	 abstract void delete(T entity);

	 abstract T find(Class<T> entityClass, Object id);

	 abstract List<T> find(Class<T> entityClass);

	 abstract List<T> find(Class<T> entityClass, int firstResult, int maxResults);

	 abstract List<T> find(String queryName,
			Map<String, Object> namedParams);

	/**
	 * 
	 * @param type
	 * @param query
	 * @param namedParams
	 * @return
	 */
	public abstract List<T> find(QueryType type, String query,
			Map<String, Object> namedParams);

	public abstract List<T> find(String queryName,
			Map<String, Object> namedParams, int firstResult, int maxResults);

	/**
	 * 
	 * @param type
	 * @param query
	 * @param namedParams
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public abstract List<T> find(QueryType type, String query,
			Map<String, Object> namedParams, int firstResult, int maxResults);

	/**
	 * 
	 * @param query
	 * @param namedParams
	 * @return
	 */
	public abstract T findFirst(String query, Map<String, Object> namedParams);

	/**
	 * 
	 * @param query
	 * @param namedParams
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public abstract T findFirst(String query, Map<String, Object> namedParams,
			int firstResult, int maxResults);

	/**
	 * 
	 * @param type
	 * @param query
	 * @param namedParams
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public abstract T findFirst(QueryType type, String query,
			Map<String, Object> namedParams, int firstResult, int maxResults);

	

}