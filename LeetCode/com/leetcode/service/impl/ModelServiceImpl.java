package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import model.Identifyable;
import service.ModelService;

public class ModelServiceImpl<T extends Identifyable> implements ModelService<T> {


    private final HashMap<Integer, T> db;

    public ModelServiceImpl() {
        db = new HashMap<>();
    }

    @Override
    public T add(T entity) {
        db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> getById(int id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(db.values());
    }

}
