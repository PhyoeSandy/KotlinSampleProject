package com.padcmyanmar.padcx.kotlinsampleproject.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.padcmyanmar.padcx.kotlinsampleproject.R
import com.padcmyanmar.padcx.kotlinsampleproject.activities.SecondActivity.Companion.IE_RETURN_DATA_FROM_SECOND
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 12/28/2019.
 */

class MainActivity : AppCompatActivity() {
    companion object{
        const val REQUEST_CODE_FROM_MAIN = 11
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            // to go Second Activity
            startActivity(SecondActivity.newIntent(this,"Hello From Main Activity"))
        }

        btnGoSecond.setOnClickListener {
            startActivityForResult(SecondActivity.newIntent(
                this,"Hi From Main"
            ), REQUEST_CODE_FROM_MAIN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode == REQUEST_CODE_FROM_MAIN && resultCode == Activity.RESULT_OK
                    && data != null ->{
                val returnData = data.getStringExtra(IE_RETURN_DATA_FROM_SECOND)
                tvHello.text = returnData
            }
        }
    }
}
