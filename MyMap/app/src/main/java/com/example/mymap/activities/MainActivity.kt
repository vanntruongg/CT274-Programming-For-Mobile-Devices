package com.example.mymap.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymap.R
import com.example.mymap.adapters.MapsAdapter
import com.example.mymap.constants.CommonConstant
import com.example.mymap.constants.LogTags
import com.example.mymap.constants.Utils
import com.example.mymap.data.DataGenerate
import com.example.mymap.databinding.ActivityMainBinding
import com.example.mymap.models.UserMap

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userMaps: MutableList<UserMap>
    private lateinit var mapAdapter: MapsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        userMaps = DataGenerate.generateSimpleData().toMutableList()

        binding.rcvMaps.layoutManager = LinearLayoutManager(this)

        mapAdapter = MapsAdapter(this, userMaps, object : MapsAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                Log.i(LogTags.TAG_MAIN_ACTIVITY, "onClick $position")

                val intent = Intent(this@MainActivity, DisplayMapActivity::class.java)
                intent.putExtra(Utils.EXTRA_USER_MAP, userMaps[position])
                startActivity(intent)
            }
        })
        binding.rcvMaps.adapter = mapAdapter

        binding.btnAdd.setOnClickListener {
            val mapFormView = LayoutInflater.from(this).inflate(R.layout.dialog_create_map, null)
            AlertDialog.Builder(this).setTitle(CommonConstant.MAP_TITLE)
                .setView(mapFormView)
                .setNegativeButton(CommonConstant.CANCEL, null)
                .setPositiveButton(CommonConstant.OK) { _, _ ->
                    val title = mapFormView.findViewById<EditText>(R.id.etTitleMap).text.toString()
                    if (title.trim().isEmpty()) {
                        Toast.makeText(this, CommonConstant.FILL_OUT_TITLE, Toast.LENGTH_SHORT)
                            .show()
                        return@setPositiveButton
                    }
                    val intent = Intent(this@MainActivity, CreateMapActivity::class.java)
                    intent.putExtra(Utils.EXTRA_MAP_TITLE, title)
                    getResult.launch(intent)
                }
                .show()
        }
    }

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val userMap = result.data?.getSerializableExtra(Utils.EXTRA_USER_MAP) as UserMap
                userMaps.add(userMap)
                mapAdapter.notifyItemInserted(userMaps.size - 1)

                Log.i(LogTags.TAG_MAIN_ACTIVITY, userMap.title)
            }
        }
}