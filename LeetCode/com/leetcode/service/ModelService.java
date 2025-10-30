package service;

import java.util.List;
import java.util.Optional;

public interface ModelService<T> {

    T add(T entity);

    Optional<T> getById(int id);

    List<T> getAll();
}
