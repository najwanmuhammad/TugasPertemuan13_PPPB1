package com.example.tugaspertemuan13


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tugaspertemuan13.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // Menggunakan View Binding untuk mengakses view secara aman dan efisien
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur NavController dari NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav) as NavHostFragment
        navController = navHostFragment.navController

        // Menghubungkan BottomNavigationView dengan NavController
        binding.bottomNavigation.setupWithNavController(navController)

        // Optional: Listener untuk menambah log atau aksi saat navigasi berubah
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Log tujuan navigasi untuk debugging
            println("Navigated to: ${destination.label}")
        }
    }
}