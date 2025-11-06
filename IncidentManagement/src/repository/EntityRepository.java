package repository;

import java.util.List;
import java.util.Optional;

public interface EntityRepository<T> {

    T save(T entity);

    Optional<T> findById(String id);

    List<T> findAll();
}
