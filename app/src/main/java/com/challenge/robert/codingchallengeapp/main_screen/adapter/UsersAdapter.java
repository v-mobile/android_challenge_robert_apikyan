package com.challenge.robert.codingchallengeapp.main_screen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.challenge.robert.codingchallengeapp.R;
import com.challenge.robert.codingchallengeapp.main_screen.pojos.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 17.09.2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    private final List<User> userList = new ArrayList<>();

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UsersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user, parent, false));
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        holder.onBind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public boolean isEmpty(){
        return userList.isEmpty();
    }

    public void addUsersAndNotify(List<User> userList) {
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

    public void addUserAndNotify(User user) {
        userList.add(user);
        notifyItemInserted(userList.size() - 1);
    }
}
