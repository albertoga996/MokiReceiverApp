package com.example.mokireceiverapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mokireceiverapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //var receiver: BroadcastReceiver? = null

    private lateinit var binding: ActivityMainBinding

    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        intent.getStringExtra("fact")?.let {
            text = it
        }
        //configureReceiver()
    }

    fun getFact() = text

    /*private fun configureReceiver() {
        val filter = IntentFilter()
        filter.addAction("com.example.broadcast.moki.MY_BROADCAST")
        receiver = MyReceiver()
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(receiver)
    }*/
}