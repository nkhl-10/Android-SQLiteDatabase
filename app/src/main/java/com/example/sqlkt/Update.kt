package com.example.sqlkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sqlkt.Sql.DbHelper
import com.example.sqlkt.Sql.User

class Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val t1=findViewById<EditText>(R.id.txt1)
        val t2=findViewById<EditText>(R.id.txt2)
        val btn=findViewById<Button>(R.id.button)


        t1.setText(intent.getStringExtra("t1"))
        t2.setText(intent.getStringExtra("t2"))
        val id:Int=intent.getIntExtra("id",0)

        btn.setOnClickListener{
            val db= DbHelper(this)
            val user = User(id, t1.text.toString(), t2.text.toString())
            db.update(user)
            showToast("Data Saved")
            startActivity(Intent(this,Show::class.java))
            finish()
        }
    }

    fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}