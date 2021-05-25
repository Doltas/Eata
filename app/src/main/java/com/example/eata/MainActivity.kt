package com.example.eata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eata.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        val userID= intent.getStringExtra("userID")
        val userPass = intent.getStringExtra("userPass")
//        val userName = intent.getStringExtra("userName")
//        val userEmail = intent.getStringExtra("userEmail")
//
//
//        binding.tvId.text = userID
//        binding.tvPw.text = userPass
//        binding.tvUsername.text =  userName
//        binding.tvAny.text = userEmail
//        binding.tvId.text = intent.getStringExtra("userID")
//        binding.tvPw.text = intent.getStringExtra("userPass")

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_home -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.action_schedule -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.action_board -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.action_notification -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.action_campuspick -> {
                    // Respond to navigation item 2 click
                    true
                }

                else -> false
            }
        }



    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}
