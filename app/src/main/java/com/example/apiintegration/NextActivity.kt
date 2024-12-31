package com.example.apiintegration

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apiintegration.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {
    lateinit var binding: ActivityNextBinding
    var first_name:String?=null
    var last_name:String?=null
    var email:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityNextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        first_name = intent.getStringExtra("first_name")
        last_name = intent.getStringExtra("last_name")
        email = intent.getStringExtra("email")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.name.text=first_name
        binding.sName.text=last_name
        binding.email.text=email
    }
}