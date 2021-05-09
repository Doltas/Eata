package com.example.eata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.eata.databinding.ActivityLoginBinding
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityLoginBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    private var userID = ""
    private var userPass = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //회원가입 클릭시
        binding.tvReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        //로그인버튼 실행
        binding.btnLogin.setOnClickListener {
            //EDITTEXT에 입력된 값 get해옴
            userID = binding.etId.text.toString()
            userPass = binding.etPw.text.toString()

            val responseListener = object : Response.Listener<String> {
                override fun onResponse(response: String) {
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success")
                        if (success) { // 로그인에 성공한 경우
                            val userID = jsonObject.getString("userID")
                            val userPass = jsonObject.getString("userPassword")
//                            val userName = jsonObject.getString("userName")
//                            val userEmail = jsonObject.getString("userEmail")
                            //php파일 변경 X 필요하면 변경


                            Toast.makeText(applicationContext, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("userID", userID)
                            intent.putExtra("userPass", userPass)
//                            intent.putExtra("userName",userName)
//                            intent.putExtra("userEmail",userEmail)


                            startActivity(intent)
                        } else { // 로그인에 실패한 경우
                            Toast.makeText(
                                applicationContext,
                                "로그인에 실패하였습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            // 서버로 Volley를 이용해서 요청을 함.
            val loginRequest = LoginRequest(userID, userPass, responseListener)
            val queue = Volley.newRequestQueue(this@LoginActivity)
            queue.add(loginRequest)


        }
    }



    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴   스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}








