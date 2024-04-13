package com.example.datlichkhambenh.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.dao.UserFireBaseDAO;
import com.example.datlichkhambenh.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> listUser;
    private UserFireBaseDAO userFireBaseDAO;
    public UserAdapter(UserFireBaseDAO userFireBaseDAO, List<User> listUser){
        this.listUser = listUser;
        this.userFireBaseDAO = userFireBaseDAO;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
