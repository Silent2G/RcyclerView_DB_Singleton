package ua.com.freearduino.myapplication.db.service;

import android.content.Context;

import java.util.List;

import ua.com.freearduino.myapplication.db.dao.UserDAO;
import ua.com.freearduino.myapplication.db.service.core.OpenDBService;
import ua.com.freearduino.myapplication.db.service.core.Service;
import ua.com.freearduino.myapplication.model.User;

public class UserService extends OpenDBService implements Service<User> {

    public UserService(Context context) {
        super(context);
    }

    @Override
    public void save(User user) {
        try {
            if (!isOpen()) {
                open();
            }
            new UserDAO(getSqLiteDatabase()).save(user);
        } finally {
            if(isOpen()) {
                close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        try {
            if (!isOpen()) {
                open();
            }
            // select
            return new UserDAO(getSqLiteDatabase()).getAll();
        } finally {
            if(isOpen()) {
                close();
            }
        }
    }
}
