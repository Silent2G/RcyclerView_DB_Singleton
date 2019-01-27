package ua.com.freearduino.myapplication;

import android.content.Context;

import java.util.ArrayList;

import ua.com.freearduino.myapplication.model.User;

public final class UserLab {
    private static UserLab userLab;
    private Context appContext;

    private ArrayList<User> userList;

    private UserLab(Context appContext) {
        this.appContext = appContext;
        this.userList = new ArrayList<>();
    }

    public static UserLab get(Context context) {
        if(userLab == null) {
            userLab = new UserLab(context.getApplicationContext());
        }
        return userLab;
    }

    private void createUsers() {
        for(int i = 1; i <= 100; i++) {
            User user = new User(i,"Name " + i, "Surname " + i, i);
            userList.add(user);
        }
    }

    public ArrayList<User> getUserList() {
        createUsers();
        return userList;
    }
}
