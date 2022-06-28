package com.dh.beTFI.dentalPractices.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    T getById(int id);
    // T getByCriteria(String criteria);
    T save(T entity);
    T update(T entity);
    void delete(int id);
}
