package br.com.crptecnologia.workshop;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, Id> {
    Optional<T> findById(Id id);
    List<T> findAll();
    T save(T entity);
}
