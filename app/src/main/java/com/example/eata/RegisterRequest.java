package com.example.eata;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
        final static private String URL = "http://doltasweb.dothome.co.kr/Register.php";
    private final Map<String, String> map;


    public RegisterRequest(String userID, String userPassword, String userName, String userEmail,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("userEmail", userEmail);

    }

    @Override
    protected Map<String, String> getParams() {
        return map;
    }
}


