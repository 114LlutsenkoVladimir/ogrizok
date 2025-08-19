package com.example.universityadmissionscommittee.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public abstract class AbstractCrudService<T, ID, R extends JpaRepository<T, ID>> {

    protected final R repository;

    protected AbstractCrudService(R repository) {
        this.repository = repository;
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }


    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public T findById(ID id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }
}
