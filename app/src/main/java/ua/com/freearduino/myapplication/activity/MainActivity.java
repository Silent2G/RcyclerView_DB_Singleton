package ua.com.freearduino.myapplication.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ua.com.freearduino.myapplication.R;
import ua.com.freearduino.myapplication.UserLab;
import ua.com.freearduino.myapplication.db.service.UserService;
import ua.com.freearduino.myapplication.model.User;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        saveUser();

        list = (ArrayList<User>) new UserService(getApplicationContext()).getAll();

        updateUI(list);
    }

    private void updateUI(ArrayList<User> list) {
        adapter = new UserAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void saveUser() {
        new UserService(MainActivity.this).save(new User(1, "Stas", "Vorobiov", 24));
        new UserService(MainActivity.this).save(new User(2, "Roman", "Krasniak", 24));
        new UserService(MainActivity.this).save(new User(3, "Bogdan", "Shuliar", 24));
    }

    private class UserHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        private TextView nameTextView;
        private TextView surnameTextView;
        private TextView ageTextView;

        private User ourUser;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            nameTextView = itemView.findViewById(R.id.name);
            surnameTextView = itemView.findViewById(R.id.surname);
            ageTextView = itemView.findViewById(R.id.age);
        }

        public void bindUser(User user) {
            ourUser = user;

            nameTextView.setText(user.getName());
            surnameTextView.setText(user.getSurname());
            ageTextView.setText(String.valueOf(user.getAge()));

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), ourUser.getName()
                    + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {

        private ArrayList<User> userList;

        public UserAdapter(ArrayList<User> userList) {
            this.userList = userList;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
            View view = layoutInflater.inflate(R.layout.item_recycler_view, viewGroup, false);
            return new UserHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder userHolder, int position) {
            User user = userList.get(position);
            userHolder.bindUser(user);
        }

        @Override
        public int getItemCount() {
            return this.userList.size();
        }
    }
}
