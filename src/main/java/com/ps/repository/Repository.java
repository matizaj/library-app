package com.ps.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    Optional<T> findById(int id);
    List<T> findAll();
    T save(T t);
    void delete(T t);
    void update(T t);
    int[] update(List<T> t);
    int[] delete(List<T> t);
}
