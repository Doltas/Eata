package com.example.eata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.example.eata.databinding.ActivityRegisterBinding
import java.util.ArrayList

class RegisterActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityRegisterBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = binding.spYear
        val cyear: Array<String> = resources.getStringArray(R.array.class_year)


        var Myadapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, cyear)
        Myadapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

        spinner.setAdapter(Myadapter)

        //spinner 클릭시
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //스피너 아이템이 선택될때
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@RegisterActivity, resources.getStringArray(R.array.class_year)[position], Toast.LENGTH_LONG)
                        .show()
            }
        // 암것도 선택 안될때
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //다음버튼 클릭시
        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.etSchool.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {



            }
        })




    }



    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}