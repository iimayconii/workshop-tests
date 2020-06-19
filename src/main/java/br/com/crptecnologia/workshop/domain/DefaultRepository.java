package br.com.crptecnologia.workshop.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public abstract class DefaultRepository<T extends DefaultEntity> implements CrudRepository<T, Long> {

    long sequence;
    Map<Long, T> db;

    public DefaultRepository() {
        this(0L, new HashMap<>());
    }

    public DefaultRepository(long sequence, Map<Long, T> db) {
        this.sequence = sequence;
        this.db = db;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return db.containsKey(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Iterable<T> findAllById(Iterable<Long> ids) {
        List<T> entities = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(x -> entities.add(x)));
        return entities;
    }

    @Override
    public long count() {
        return db.size();
    }

    @Override
    public void deleteById(Long id) {
        Optional<T> entity = findById(id);
        entity.ifPresent(x -> db.remove(x.getId(), x));
    }

    @Override
    public void delete(T t) {
        deleteById(t.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        iterable.forEach(x -> db.remove(x.getId(), x));
    }

    @Override
    public void deleteAll() {
        db.clear();
    }

    @Override
    public <S extends T> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(++sequence);
        }
        db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        List<S> entities = new ArrayList<>();
        iterable.forEach(x -> entities.add(save(x)));
        return entities;
    }
}
