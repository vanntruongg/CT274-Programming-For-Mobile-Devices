package com.example.b2012051_tranvantruong_uocmo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.b2012051_tranvantruong_uocmo.databinding.ItemWishBinding
import com.example.b2012051_tranvantruong_uocmo.models.Wish
import com.example.b2012051_tranvantruong_uocmo.sharedpreferences.AppSharedPreferences
import com.example.b2012051_tranvantruong_uocmo.R

class WishAdapter(
    private val context: Context,
    private val wishList: List<Wish>,
    private val appSharedPreferences: AppSharedPreferences,
    private val iClickItemWish: IClickItemWish
) : RecyclerView.Adapter<WishAdapter.WishViewHolder>() {

    class WishViewHolder(val binding: ItemWishBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishViewHolder {
        return WishViewHolder(
            ItemWishBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return wishList.size
    }

    override fun onBindViewHolder(holder: WishViewHolder, position: Int) {
        // lay doi tuong wish o vi tri position va thiet lap len giao dien
        val wish: Wish = wishList[position]
        holder.binding.tvName.text = wish.name
        holder.binding.tvContent.text = wish.content

        Glide.with(context).load(wish.owner.avatar)
            .error(R.drawable.avt_default)
            .into(holder.binding.imgAvatar)

        // neu nguoi dung dang dang nhap la chu nhan cua dieu uoc o vi tri position thi hien thi update & delete
        if(appSharedPreferences.getIdUser("idUser").toString() == wish.owner._id) {
            holder.binding.imgUpdate.visibility = View.VISIBLE
            holder.binding.imgDelete.visibility = View.VISIBLE
        } else {
            holder.binding.imgUpdate.visibility = View.GONE
            holder.binding.imgDelete.visibility = View.GONE
        }

        // bat su kien click vao nut xoa va cap nhat

        holder.binding.imgUpdate.setOnClickListener {
            iClickItemWish.onClickUpdate(wish._id, wish.name, wish.content)
        }

        holder.binding.imgDelete.setOnClickListener {
            iClickItemWish.onClickRemove(wish._id)
        }

    }

    interface IClickItemWish {
        fun onClickUpdate(idWish: String, fullName: String, content: String)
        fun onClickRemove(idWish: String)
    }
}