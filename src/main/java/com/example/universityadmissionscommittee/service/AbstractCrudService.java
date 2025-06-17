package com.example.universityadmissionscommittee.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T, ID, R extends JpaRepository<T, ID>> {

    protected final R repository;

    protected AbstractCrudService(R repository) {
        this.repository = repository;
    }


    public T save(T entity) {
        return repository.save(entity);
    }


    public void deleteById(ID id) {
        repository.deleteById(id);
    }


    public T findById(ID id) {
        return repository.findById(id).orElseThrow();
    }


    public List<T> findAll() {
        return repository.findAll();
    }
}
