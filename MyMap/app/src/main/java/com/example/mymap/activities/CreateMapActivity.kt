package com.example.mymap.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.mymap.R
import com.example.mymap.constants.CommonConstant
import com.example.mymap.constants.LogTags
import com.example.mymap.constants.Utils

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mymap.databinding.ActivityCreateMapBinding
import com.example.mymap.models.Place
import com.example.mymap.models.UserMap
import com.google.android.gms.maps.model.Marker
import com.google.android.material.snackbar.Snackbar

class CreateMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityCreateMapBinding
    private var markers: MutableList<Marker> = mutableListOf<Marker>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(Utils.EXTRA_MAP_TITLE)

        supportActionBar?.title = title

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mapFragment.view?.let {
            Snackbar.make(it, CommonConstant.LONG_PRESS_ADD_MARKER, Snackbar.LENGTH_INDEFINITE)
                .setAction(CommonConstant.OK, {})
                .setActionTextColor(ContextCompat.getColor(this, R.color.white))
                .show()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnInfoWindowClickListener { marker ->
            Log.i(LogTags.TAG_CREATE_MAP_ACTIVITY, "setOnInfoWindowClickListener - Delete")
            markers.remove(marker)
            marker.remove()
        }
        mMap.setOnMapLongClickListener { latLng ->
            Log.i(LogTags.TAG_CREATE_MAP_ACTIVITY, "setOnMapLongClickListener")
            val placeFromView =
                LayoutInflater.from(this).inflate(R.layout.dialog_create_place, null)

            AlertDialog.Builder(this).setTitle(CommonConstant.CREATE_MARKER)
                .setView(placeFromView)
                .setNegativeButton(CommonConstant.CANCEL, null)
                .setPositiveButton(CommonConstant.OK) { _, _ ->
                    val title = placeFromView.findViewById<EditText>(R.id.etTitle).text.toString()
                    val description =
                        placeFromView.findViewById<EditText>(R.id.etDescription).text.toString()

                    if (title.trim().isEmpty() || description.trim().isEmpty()) {
                        Toast.makeText(
                            this,
                            CommonConstant.FILL_OUT_TITLE_AND_DESCRIPTION,
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setPositiveButton
                    }
                    val marker = mMap.addMarker(
                        MarkerOptions().position(latLng).title(title).snippet(description)
                    )
                    markers.add(marker!!)
                }
                .show()
        }

        val ctu = LatLng(10.031452976258134, 105.77197889530333)
        mMap.addMarker(MarkerOptions().position(ctu).title("Trường ĐH Cần Thơ"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ctu, 10f))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_map, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miSave) {
            Log.i(LogTags.TAG_CREATE_MAP_ACTIVITY, CommonConstant.CLICKED_ON_SAVE)
            if (markers.isEmpty()) {
                Toast.makeText(this, CommonConstant.MINIMUM_MARKERS_REQUIRED, Toast.LENGTH_SHORT)
                    .show()
                return true
            }
            val places = markers.map { it ->
                Place(it.title!!, it.snippet!!, it.position.latitude, it.position.longitude)
            }
            val userMap = UserMap(intent.getStringExtra(Utils.EXTRA_MAP_TITLE)!!, places)
            val data = Intent()
            data.putExtra(Utils.EXTRA_USER_MAP, userMap)
            setResult(Activity.RESULT_OK, data)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}