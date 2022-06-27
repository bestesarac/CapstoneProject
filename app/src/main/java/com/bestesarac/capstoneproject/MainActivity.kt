package com.bestesarac.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bestesarac.capstoneproject.R
import com.bestesarac.capstoneproject.databinding.ActivityMainBinding
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appBarConfiguration=AppBarConfiguration(
            setOf(
                R.id.signupFragment,
                R.id.homeFragment,
                R.id.favoriteFragment,
                R.id.bagFragment,
                R.id.paymentFragment

            )
        )

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView)as NavHost

        navController=navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()|| super.onSupportNavigateUp()
        }


}
