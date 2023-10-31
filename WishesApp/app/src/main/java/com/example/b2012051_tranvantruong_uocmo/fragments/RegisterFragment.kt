package com.example.b2012051_tranvantruong_uocmo.fragments

import android.os.Bundle
import android.provider.SyncStateContract.Constants
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.b2012051_tranvantruong_uocmo.R
import com.example.b2012051_tranvantruong_uocmo.constants.ObjectConstants
import com.example.b2012051_tranvantruong_uocmo.databinding.FragmentRegisterBinding
import com.example.b2012051_tranvantruong_uocmo.models.RequestRegisterOrLogin
import com.example.b2012051_tranvantruong_uocmo.sharedpreferences.AppSharedPreferences
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding
    private lateinit var mAppSharedPreferences : AppSharedPreferences
    private var username = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        mAppSharedPreferences = AppSharedPreferences(requireContext())

        binding.apply {
            btnRegister.setOnClickListener {
                if(editUserName.text.isNotEmpty()) {
                    username = editUserName.text.toString().trim()
                    registerUser(username)
                } else {
                    Snackbar.make(it, "Vui lòng nhập mã số sinh viên!", Snackbar.LENGTH_LONG).show()
                }
            }

            tvLogin.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, LoginFragment())
                    .commit()
            }
        }

        return binding.root
    }

    private fun registerUser(username : String) {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                val response = ObjectConstants.getInstance().registerUser(RequestRegisterOrLogin(username)).body()

                if(response != null) {
                    if(response.success) {
                        mAppSharedPreferences.putIdUser("idUser", response.idUser!!)
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, WishListFragment())
                            .commit()
                        progressBar.visibility = View.GONE
                    } else {
                        tvMessage.text = response.message
                        tvMessage.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

}