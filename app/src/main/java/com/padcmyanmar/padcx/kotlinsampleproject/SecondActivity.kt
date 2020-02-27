package com.padcmyanmar.padcx.kotlinsampleproject.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.padcmyanmar.padcx.kotlinsampleproject.R
import kotlinx.android.synthetic.main.activity_second.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 12/28/2019.
 */
class SecondActivity : AppCompatActivity() {
    val REQUEST_CODE_GET_IMAGES = 10

    companion object{
        val IE_DATA_FROM_MAIN = "IE_DATA_FROM_MAIN"
        val IE_RETURN_DATA_FROM_SECOND = "IE_RETURN_DATA_FROM_SECOND"

        fun newIntent(context: Context) : Intent {
            return Intent(context,SecondActivity::class.java)
        }

        fun newIntent(context: Context,dataToDisplay: String) : Intent{
            val intent = Intent(context,SecondActivity::class.java)
            intent.putExtra(IE_DATA_FROM_MAIN,dataToDisplay)
            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnBackMain.setOnClickListener {
            // destory current activity
            //finish()

            // parse data to previous activity
            val intent = Intent()
            intent.putExtra(IE_RETURN_DATA_FROM_SECOND,"Hello back From Second")
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

        btnGoMap.setOnClickListener {
            val mapPrefix = "http://maps.google.com/maps?addr="
            val uriToOpen = "$mapPrefix PADC Myanmar"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uriToOpen)
            // check support func
            when{
                intent.resolveActivity(packageManager) != null -> {
                    startActivity(intent)
                }
            }
        }
        // retrieve from Intent
        tvMainData.text = intent.getStringExtra(IE_DATA_FROM_MAIN)

        btnSelectImage.setOnClickListener {
            // select image from device
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*" // all image type
            startActivityForResult(intent,REQUEST_CODE_GET_IMAGES)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_GET_IMAGES && resultCode == Activity.RESULT_OK
            && data != null){
            val fullImageUri = data.data
            ivSelectedImage.setImageURI(fullImageUri)
        }
    }
}