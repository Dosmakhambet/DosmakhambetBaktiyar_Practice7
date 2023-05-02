package com.dosmakhambetbaktiyar.repository;

import java.util.List;

public interface GenericRepository <T,ID>{
    T create(T t);

    T get(ID id);

    List<T> getAll();

    boolean delete(ID id);

    T update(T t);
}