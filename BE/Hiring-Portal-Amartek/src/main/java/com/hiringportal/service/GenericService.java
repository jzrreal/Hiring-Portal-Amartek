package com.hiringportal.service;

import java.util.List;

public interface GenericService <E, T>{

    public List<E> getAll();
    public E getById(T id);
    public E save(E entity);
    public E update(E entity);
    public Boolean delete(T id);
}
