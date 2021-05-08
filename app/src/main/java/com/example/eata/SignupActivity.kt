package com.example.eata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.eata.databinding.ActivitySignupBinding
import org.json.JSONException
import org.json.JSONObject

class SignupActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivitySignupBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!
    private var userID = ""
    private var userPass = ""
    private var userName = ""
    private var userEmail = ""





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


         binding.signupBtn.setOnClickListener(View.OnClickListener {
             //회원가입 처리 시작
             //EDITTEXT에 입력된 값 get해옴
             userID = binding.etId.text.toString()
             userPass = binding.etPw.text.toString()
             userName = binding.etNickname.text.toString()
             userEmail = binding.etEmail.text.toString()

             val responseListener = object: Response.Listener<String> {
                 override fun onResponse(response:String) {
                     try
                     {
                         val jsonObject = JSONObject(response)
                         val success = jsonObject.getBoolean("success")
                         if (success)
                         { // 회원등록에 성공한 경우
                             Toast.makeText(applicationContext, "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                             val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                             startActivity(intent)
                         }
                         else
                         { // 회원등록에 실패한 경우
                             Toast.makeText(applicationContext, "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                             return
                         }
                     }
                     catch (e: JSONException) {
                         e.printStackTrace()
                     }
                 }
             }
             // 서버로 Volley를 이용해서 요청을 함.
             val registerRequest = RegisterRequest(userID, userPass, userName,userEmail, responseListener)
             val queue = Volley.newRequestQueue(this@SignupActivity)
             queue.add(registerRequest)


         })
     }











    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}
