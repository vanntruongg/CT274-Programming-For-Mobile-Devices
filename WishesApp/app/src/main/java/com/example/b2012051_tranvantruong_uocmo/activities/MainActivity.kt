package com.example.b2012051_tranvantruong_uocmo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.b2012051_tranvantruong_uocmo.R
import com.example.b2012051_tranvantruong_uocmo.databinding.ActivityMainBinding
import com.example.b2012051_tranvantruong_uocmo.fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, LoginFragment()).commit()
    }
}