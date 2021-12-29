package com.example.loginusingretrofit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginusingretrofit.R
import com.example.loginusingretrofit.api.RetrofitClient
import com.example.loginusingretrofit.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener{

            val email = edt_email.text.toString().trim()
            val password = edt_password.text.toString().trim()

            if (email.isEmpty()){
                edt_email.error = "Nhap Email"
                edt_email.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                edt_password.error = "Nhap Password"
                edt_password.requestFocus()
                return@setOnClickListener
            }


            RetrofitClient.instance.createUser(email, password)
                .enqueue(object : Callback<DefaultResponse>{
                    override fun onResponse( call: Call<DefaultResponse>, response: Response<DefaultResponse>
                    ) {
                        if(response.isSuccessful){
                            Toast.makeText(applicationContext, response.body()?.token, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message    , Toast.LENGTH_SHORT).show()
                    }

                })
        }


        tv_register.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}