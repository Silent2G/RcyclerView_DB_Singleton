package ua.com.freearduino.myapplication.db.service.core;

import java.util.List;

public interface Service<T> {
    void save(T t);
    List<T> getAll();
}
