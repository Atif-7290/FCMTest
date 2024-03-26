package com.example.fcmtest

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
                // Permission granted, perform the notification-related tasks here
            } else {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
                // Permission denied
                // Handle the case where the user denies the permission
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        checkNotificationPermission()

    }

    private fun checkNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        } else {
            Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
            // Permission has already been granted
            // You can perform your notification-related tasks here
        }
    }

   /*private fun checkNotificationPermission(){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.SYSTEM_ALERT_WINDOW),1000
            )
        }
        else{

        }
    }*/

    /*private fun requestPermissions(permissions: Array<String>) {
        requestMultiplePermissionsLauncher.launch(permissions)
    }*/
}