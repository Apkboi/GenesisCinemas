package com.example.genesiscinemas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class UserListAdapter  extends RecyclerView.Adapter<UserListAdapter.ListItemHolder>{

    ArrayList<UserData> Users = new ArrayList<>();
    public UserListAdapter() {
        this.Users = UserData.getData();
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.useralayout,parent,false);
       return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {

        UserData datas = Users.get(position);
        holder.btn_tickets.setText(datas.Tickets);
        holder.Username.setText(datas.Username);
        holder.userImage.setImageResource(datas.UserImage);
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

     public class ListItemHolder extends RecyclerView.ViewHolder{
            private ImageView userImage; private ImageView ImgDel;
            private MaterialButton btn_tickets;
            private TextView Username;
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);


            userImage= itemView.findViewById(R.id.UserImg);
            Username=itemView.findViewById(R.id.Username);
            ImgDel=itemView.findViewById(R.id.ImgDel);
            btn_tickets=itemView.findViewById(R.id.btn_tickets);

        }
    }

    static class UserData{
        int UserImage;
        String Username;
        String Tickets;

        public UserData(int userImage, String username, String tickets) {
            UserImage = userImage;
            Username = username;
            Tickets = tickets;
        }

        public static ArrayList<UserData> getData (){
            ArrayList<UserData> User = new ArrayList<>();
            User.add(new UserData(R.drawable.xx,"ebube Roderick","55 tickets"));
            User.add(new UserData(R.drawable.xx,"ebube Roderick","55 tickets"));
            User.add(new UserData(R.drawable.xx,"ebube Roderick","55 tickets"));
            User.add(new UserData(R.drawable.xx,"ebube Roderick","55 tickets"));
            User.add(new UserData(R.drawable.xx,"ebube Roderick","55 tickets"));
            User.add(new UserData(R.drawable.xx,"ebube Roderick","55 tickets"));
            User.add(new UserData(R.drawable.xx,"ebube Roderick","55 tickets"));
            User.add(new UserData(R.drawable.xx,"ebube Roderick","55 tickets"));
            return User;
        }
    }
}
