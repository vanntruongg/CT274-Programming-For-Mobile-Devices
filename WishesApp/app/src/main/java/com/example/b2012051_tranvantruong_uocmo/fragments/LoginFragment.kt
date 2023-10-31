package com.example.b2012051_tranvantruong_uocmo.fragments

import android.os.Bundle
import android.provider.SyncStateContract.Constants
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.b2012051_tranvantruong_uocmo.R
import com.example.b2012051_tranvantruong_uocmo.constants.ObjectConstants
import com.example.b2012051_tranvantruong_uocmo.databinding.FragmentLoginBinding
import com.example.b2012051_tranvantruong_uocmo.models.RequestRegisterOrLogin
import com.example.b2012051_tranvantruong_uocmo.sharedpreferences.AppSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mAppSharedPreferences : AppSharedPreferences
    private var username = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        mAppSharedPreferences = AppSharedPreferences(requireContext())

        binding.apply {
            tvRegister.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, RegisterFragment())
                    .commit()
            }

            btnLogin.setOnClickListener {
                if(editUserName.text.isNotEmpty()) {
                    username = editUserName.text.toString().trim()
                    loginUser(username)
                }
            }
        }

        return binding.root
    }

    private fun loginUser(username : String) {
        binding.apply { 
            progressBar.visibility  = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    val response = ObjectConstants.getInstance().loginUser(RequestRegisterOrLogin(username))
                        .body()
                    if(response != null) {
                        if (response.success) {
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

}