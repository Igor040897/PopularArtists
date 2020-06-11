package com.example.popularartists.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.popularartists.R
import com.example.popularartists.databinding.ActivityMainBinding
import com.example.popularartists.ui.base.BaseActivity
import com.example.popularartists.ui.popularArtists.PopularArtistsFragment
import dagger.android.AndroidInjection


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val contentLayoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

//        val newInstance = PopularArtistsFragment.newInstance()
//        startFragment(newInstance)
    }

    override fun setupBinding(binding: ActivityMainBinding) {
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(
            this,
            findNavController(R.id.nav_host_fragment),
            null
        )
    }
//    private fun startFragment(fragment: Fragment){
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.container, fragment)
//            .commit()
//    }
}