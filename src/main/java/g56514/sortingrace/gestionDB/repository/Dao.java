package g56514.sortingrace.gestionDB.repository;

import g56514.sortingrace.gestionDB.dto.Dto;
import g56514.sortingrace.gestionDB.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author yohan
 */
public interface Dao<K, T extends Dto<K>> {

    K insert(T item) throws RepositoryException;

    void delete(K key) throws RepositoryException;

    void update(T item) throws RepositoryException;

    List<T> selectAll() throws RepositoryException;

    T select(K key) throws RepositoryException;
}
