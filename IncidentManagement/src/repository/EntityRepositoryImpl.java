package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import entitiy.Identifyable;

public class EntityRepositoryImpl<T extends Identifyable> implements EntityRepository<T> {

    private final HashMap<String, T> db;

    

    public EntityRepositoryImpl() {
        this.db = new HashMap<>();
    }

    @Override
    public T save(T entity) {
        db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(db.values());
    }

}
