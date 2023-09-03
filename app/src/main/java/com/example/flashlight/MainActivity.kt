package com.example.flashlight

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val onButton= findViewById<Button>(R.id.flash_on_btn)
        val ofButton=findViewById<Button>(R.id.flash_of_btn)


        onButton.setOnClickListener{
            onFlash()
        }
        ofButton.setOnClickListener{
            offFlash()
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onFlash(){
        var cameraManager: CameraManager?=null
        cameraManager = getSystemService(CAMERA_SERVICE)as CameraManager

        try {
            var cameraId : String? = null
            cameraId = cameraManager.cameraIdList[0]
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                cameraManager!!.setTorchMode(cameraId, true)
              Toast.makeText(this,"Flash ON",Toast.LENGTH_SHORT).show()
            }

        }catch (e:CameraAccessException){
           /* Toast.makeText(this,"Exception:"+e.message).show()*/
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun offFlash(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            val cameraManager= getSystemService(CAMERA_SERVICE) as CameraManager

            try {
                val cameraId = cameraManager.cameraIdList[0]
                cameraManager.setTorchMode(cameraId,false)
                Toast.makeText(this,"Flash OFF",Toast.LENGTH_SHORT).show()
            }catch (e:CameraAccessException){

            }
        }
    }
}