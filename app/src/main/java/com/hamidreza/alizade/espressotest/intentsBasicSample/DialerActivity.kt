/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hamidreza.alizade.esperssotest.intentsBasicSample

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hamidreza.alizade.esperssotest.R

/**
 * Simple Dialer Activity which shows an [EditText] field to enter a phone number. Upon
 * pressing the call button the number entered in the input field is send to the native Android
 * Dialer app via [Intent.ACTION_CALL].
 *
 *
 *
 * Furthermore this Activity contains a pick number button to starts dummy contacts activity to
 * demonstrate Intent stubbing.
 */
class DialerActivity : Activity() {

    private var mCallerNumber: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialer)
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
        mCallerNumber = findViewById<View>(R.id.edit_text_caller_number) as EditText

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }


    fun per(){

    }

    fun onCall(view: View) {
        val hasCallPhonePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

        if (hasCallPhonePermission)
            startActivity(createCallIntentFromNumber())
        else
            Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show()
    }

    fun onPickContact(view: View) {
        val pickContactIntent = Intent(this, ContactsActivity::class.java)
        startActivityForResult(pickContactIntent, REQUEST_CODE_PICK)
    }

    private fun createCallIntentFromNumber(): Intent {
        val intentToCall = Intent(Intent.ACTION_CALL)
        val number = mCallerNumber!!.text.toString()
        intentToCall.data = Uri.parse("tel:$number")
        return intentToCall
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == Activity.RESULT_OK) {
                mCallerNumber!!.setText(
                    data.extras!!
                        .getString(ContactsActivity.KEY_PHONE_NUMBER)
                )
            }
        }
    }

    companion object {

        private val REQUEST_CODE_PICK = 16
    }
}