package com.example.sqlkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sqlkt.Sql.DbHelper
import com.example.sqlkt.Sql.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val t1=findViewById<EditText>(R.id.txt1)
        val t2=findViewById<EditText>(R.id.txt2)
        val btn=findViewById<Button>(R.id.button)



        btn.setOnClickListener{
            val db=DbHelper(this)
            val user = User(0, t1.text.toString(), t2.text.toString())
            db.save(user)
            showToast("Data Saved")
            startActivity(Intent(this,Show::class.java))
        }

    }

    fun  showToast(msg:String){
        Toast.makeText(this, "$msg", Toast.LENGTH_SHORT).show()
    }
}