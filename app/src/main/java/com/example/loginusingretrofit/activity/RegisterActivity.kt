package com.example.loginusingretrofit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginusingretrofit.R
import com.example.loginusingretrofit.api.RetrofitClient
import com.example.loginusingretrofit.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edt_email
import kotlinx.android.synthetic.main.activity_login.edt_password
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_create.setOnClickListener{

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
                .enqueue(object : Callback<DefaultResponse> {
                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        Toast.makeText(applicationContext, "response.body()?.error", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                })
        }


        tv_cancel.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }
}