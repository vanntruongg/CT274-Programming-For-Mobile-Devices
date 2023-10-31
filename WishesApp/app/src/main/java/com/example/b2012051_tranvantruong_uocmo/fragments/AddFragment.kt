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
import com.example.b2012051_tranvantruong_uocmo.models.RequestAddWish
import com.example.b2012051_tranvantruong_uocmo.sharedpreferences.AppSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var mAppSharedPreferences: AppSharedPreferences
    private var idUser = ""
    private var fullName = ""
    private var content = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        mAppSharedPreferences = AppSharedPreferences(requireContext())
        idUser = mAppSharedPreferences.getIdUser("idUser").toString()

        binding.apply {
            btnSave.setOnClickListener {
                if (editFullName.text.isNotEmpty() && editContent.text.isNotEmpty()) {
                    fullName = editFullName.text.toString().trim()
                    content = editContent.text.toString().trim()
                    addWish(fullName, content)
                } else {
                    Toast.makeText(requireContext(), "Vui lòng nhập tên và điều ước", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    private fun addWish(fullName: String, content: String) {
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val response = ObjectConstants.getInstance().addWish(RequestAddWish(idUser, fullName, content)).body()

                if(response != null) {
                    binding.progressBar.visibility = View.GONE
                    if(response.success) {
//                        binding.progressBar.visibility = View.GONE
                        // them dieu uoc thanh cong
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, WishListFragment()).commit()
                    } else {
//                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, LoginFragment()).commit()
                    }
                }
            }
        }

    }

}