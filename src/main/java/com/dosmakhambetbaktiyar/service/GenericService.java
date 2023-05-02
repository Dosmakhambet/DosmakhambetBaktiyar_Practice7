package com.dosmakhambetbaktiyar.service;

import java.util.List;

public interface GenericService <T,ID>{
    T create(T t);

    T get(ID id);

    List<T> getAll();

    boolean delete(ID id);

    T update(T t);
}