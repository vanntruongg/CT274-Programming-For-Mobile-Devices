package com.example.b2012051_tranvantruong_uocmo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.b2012051_tranvantruong_uocmo.R
import com.example.b2012051_tranvantruong_uocmo.adapters.WishAdapter
import com.example.b2012051_tranvantruong_uocmo.constants.ObjectConstants
import com.example.b2012051_tranvantruong_uocmo.databinding.FragmentWishListBinding
import com.example.b2012051_tranvantruong_uocmo.models.RequestDeleteWish
import com.example.b2012051_tranvantruong_uocmo.models.Wish
import com.example.b2012051_tranvantruong_uocmo.sharedpreferences.AppSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WishListFragment : Fragment() {
    private lateinit var binding: FragmentWishListBinding
    private lateinit var mWishList: ArrayList<Wish>
    private lateinit var mWishAdapter: WishAdapter
    private lateinit var mAppSharedPreferences: AppSharedPreferences
    private var idUser = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentWishListBinding.inflate(layoutInflater, container, false)

        mAppSharedPreferences = AppSharedPreferences((requireActivity()))
        idUser = mAppSharedPreferences.getIdUser("idUser").toString()

        mWishList = ArrayList()

        getWishList()

        binding.btnAdd.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AddFragment())
                .commit()
        }

        return binding.root
    }

    private fun getWishList() {
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val response = ObjectConstants.getInstance().getWishList().body()
                if (response != null) {
                    mWishList.addAll(response)
                    initAdapterAndSetLayout()
                }
            }
        }
    }

    private fun initAdapterAndSetLayout() {
        if(context == null) return

        mWishAdapter = WishAdapter(requireActivity(), mWishList, mAppSharedPreferences, object : WishAdapter.IClickItemWish {
            override fun onClickUpdate(idWish: String, fullName: String, content: String) {
                // luu thong tin dieu uoc vao mAppSharedPreferences
                // va chuyen vao man hinh cap nhat dieu uoc
                mAppSharedPreferences.putWish("idWish", idWish)
                mAppSharedPreferences.putWish("fullName", fullName)
                mAppSharedPreferences.putWish("content", content)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, UpdateFragment())
                    .commit()
            }

            override fun onClickRemove(idWish: String) {
                // call api xoa dieu uoc
                deleteWish(idWish)
            }
        })
        binding.rcvWishList.adapter = mWishAdapter
        binding.rcvWishList.layoutManager = LinearLayoutManager(requireActivity())
        binding.progressBar.visibility = View.GONE
    }

    private fun deleteWish(idWish: String) {
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val response = ObjectConstants.getInstance().deleteWish(RequestDeleteWish(idUser, idWish)).body()

                if (response != null) {
                    if (response.success) {
                        binding.progressBar.visibility = View.GONE
                        // xoa dieu uoc thanh cong
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                        // load lai man hinh wish list
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, WishListFragment())
                            .commit()
                    } else {
                        binding.progressBar.visibility = View.GONE
                        // xoa dieu uoc khong thanh cong
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, LoginFragment())
                            .commit()
                    }
                }
            }
        }
    }

}