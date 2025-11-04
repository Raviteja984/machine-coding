package repository;

import java.util.List;
import java.util.Optional;

public interface EntityRepository<T> {

    T addEntity(T entity);

    Optional<T> findById(int id);

    List<T> findAll();
}
