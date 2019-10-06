package com.hamidreza.alizade.esperssotest

import android.Manifest
import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest.permission
import android.Manifest.permission.WRITE_CALENDAR
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private lateinit var managePermissions: ManagePermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signin.setOnClickListener {
            Toast.makeText(this@MainActivity, "Sign In Button Clicked", Toast.LENGTH_LONG)
                .show()

        }
        signup.setOnClickListener {
            Log.e("MainActivity", "MainActivity")
            Toast.makeText(this@MainActivity, "Sign In Button Clicked", Toast.LENGTH_LONG)
                .show()
        }


    }
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {


        when (requestCode) {
            requestCode ->{
                val isPermissionsGranted = managePermissions
                    .processPermissionsResult(requestCode,permissions,grantResults)

                if(isPermissionsGranted){
                    // Do the task now

                    
                }else{
                }
                return
            }
        }


//        when (requestCode) {
//            PERMISSION_LOCATION_REQUEST_CODE -> {
//
//                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    //permission denied by user
//                    dismissSnackBar()
//                    AlertDialog.Builder(this)
//                        .setMessage(R.string.error_closing_no_location_permission)
//                        .setPositiveButton(R.string.close) { _, _ -> finish() }
//                        .setNegativeButton(R.string.enable) {_, _ ->
//                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                            intent.data = Uri.fromParts("package", packageName, null)
//                            startActivity(intent)
//                        }
//                        .setCancelable(false)
//                        .create()
//                        .show()
//                } else {
//                    //permission granted
//                    dismissSnackBar()
//                    handleGetLocation()
//                }
//            }
//        }
    }



}
