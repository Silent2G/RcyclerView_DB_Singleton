package ua.com.freearduino.myapplication.db.dao.core;

import android.database.Cursor;

import java.util.List;

public interface DAO<T> {

    void save(T t);
    List<T> getAll();
    List<T> parceCursor(Cursor cursor);
}
