package com.example.b2012051_tranvantruong_uocmo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.b2012051_tranvantruong_uocmo.R
import com.example.b2012051_tranvantruong_uocmo.constants.ObjectConstants
import com.example.b2012051_tranvantruong_uocmo.databinding.FragmentAddBinding
import com.example.b2012051_tranvantruong_uocmo.databinding.FragmentUpdateBinding
import com.example.b2012051_tranvantruong_uocmo.models.RequestUpdateWish
import com.example.b2012051_tranvantruong_uocmo.sharedpreferences.AppSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var mAppSharedPreferences: AppSharedPreferences
    private var idUser = ""
    private var idWish = ""
    private var fullName = ""
    private var content = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)

        mAppSharedPreferences = AppSharedPreferences(requireContext())
        idUser = mAppSharedPreferences.getIdUser("idUser").toString()
        idWish = mAppSharedPreferences.getWish("idWish").toString()
        fullName = mAppSharedPreferences.getWish("fullName").toString()
        content = mAppSharedPreferences.getWish("content").toString()

        binding.editFullName.setText(fullName)
        binding.editContent.setText(content)

        binding.apply {
            btnSave.setOnClickListener {
                if (editFullName.text.isNotEmpty() && editContent.text.isNotEmpty()) {
                    fullName = editFullName.text.toString().trim()
                    content = editContent.text.toString().trim()

                    updateWish(fullName, content)
                }
            }
        }

        return binding.root
    }

    private fun updateWish(fullName: String, content: String) {
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val response = ObjectConstants.getInstance()
                    .updateWish(RequestUpdateWish(idUser, idWish, fullName, content)).body()

                if (response != null) {
                    binding.progressBar.visibility = View.GONE
                    if (response.success) {
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, WishListFragment()).commit()
                    } else {
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, LoginFragment()).commit()
                    }
                }
            }
        }
    }
}
