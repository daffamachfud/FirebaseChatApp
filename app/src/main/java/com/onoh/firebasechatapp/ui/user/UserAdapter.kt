package com.onoh.firebasechatapp.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onoh.firebasechatapp.R
import com.onoh.firebasechatapp.data.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter:RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var listUser = ArrayList<User?>()

    fun setUser(users: List<User?>){
        listUser.clear()
        listUser.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = listUser[position]
        if (user != null) {
            holder.bind(user)
        }
    }

    override fun getItemCount(): Int = listUser.size


    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(user: User){
            with(itemView) {
                tv_user_name.text = user.username.toString()
                Glide.with(context)
                    .load(user.profileImage)
                    .placeholder(R.drawable.ic_account_circle)
                    .into(img_profile_user)
            }
        }
    }


}