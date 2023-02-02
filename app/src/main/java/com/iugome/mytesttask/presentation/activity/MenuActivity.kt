package com.iugome.mytesttask.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AppCompatActivity
import com.iugome.mytesttask.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }
        binding.button2.setOnClickListener {
            startActivity(Intent(applicationContext,SaveActivity::class.java))
        }

        binding.button3.setOnClickListener {
            Process.killProcess(Process.myPid())
            finish()
        }
    }
}