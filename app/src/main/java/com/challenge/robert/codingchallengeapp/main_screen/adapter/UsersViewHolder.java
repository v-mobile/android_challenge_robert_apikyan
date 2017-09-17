package com.challenge.robert.codingchallengeapp.main_screen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.challenge.robert.codingchallengeapp.R;
import com.challenge.robert.codingchallengeapp.main_screen.pojos.User;

/**
 * Created by Robert on 17.09.2017.
 */

class UsersViewHolder extends RecyclerView.ViewHolder {
    private final TextView userTV;

    UsersViewHolder(View itemView) {
        super(itemView);
        userTV = (TextView) itemView.findViewById(R.id.layout_user_tv);
    }

    void onBind(User user) {
        userTV.setText(user.getFullName());
    }
}
