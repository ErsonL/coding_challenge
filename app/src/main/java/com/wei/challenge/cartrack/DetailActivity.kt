package com.wei.challenge.cartrack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.wei.challenge.cartrack.map.OnMapAndViewReadyListener
import com.wei.challenge.cartrack.model.User
import com.wei.challenge.cartrack.model.UserListViewModel
import com.wei.challenge.cartrack.ui.MarginItemDecoration
import com.wei.challenge.cartrack.ui.UsersAdapter
import com.wei.challenge.cartrack.utility.animateCamera


class DetailActivity : AppCompatActivity(),
    OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener{

    private lateinit var usersList: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var goPageList: Button

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        OnMapAndViewReadyListener(mapFragment, this)

        goPageList = findViewById(R.id.goPageList)
        goPageList.setOnClickListener {
            PageListActivity.open(this)
        }
        setupRecyclerView()


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val model = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        model.getUsers()?.observe(this, Observer { users ->
            usersAdapter.setItems(users)
            if(users.isNotEmpty()){
                addMarkersToMap(users)
                setMapBound(users)
            }
        })

    }


    private fun addMarkersToMap(users: List<User>) {

        for (user in users){
            mMap.addMarker(MarkerOptions()
                    .position(LatLng(user.address.geo.lat.toDouble(), user.address.geo.lng.toDouble()))
                    .title(user.name))
        }

    }

    private fun setMapBound(result: List<User>) {
        val bounds = LatLngBounds.Builder()
        for(user in result){
            bounds.include(LatLng(user.address.geo.lat.toDouble(),user.address.geo.lng.toDouble()))
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
    }

    private fun setupRecyclerView() {
        usersList = findViewById(R.id.users_list)
        usersList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        usersList.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_padding).toInt()))
        usersList.layoutManager = layoutManager
        usersAdapter = UsersAdapter {
            locateToSpot(it)
        }
        usersList.adapter = usersAdapter
    }

    private fun locateToSpot(index: Int) {
        val selectedItem = usersAdapter?.getItems()?.get(index)
        if( selectedItem != null){
            val targetSpot = selectedItem.address.geo
            animateCamera(mMap, LatLng(targetSpot.lat.toDouble(),targetSpot.lng.toDouble()))
        }
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }
    }
}
