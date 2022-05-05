package repository;

import java.util.Collection;

public interface Repository<T, Tid> {
    void add(T elem);
    void delete(Tid id);
    void update(T elem, Tid id);
    T findById(Tid id);
    Collection<T> getAll();

}