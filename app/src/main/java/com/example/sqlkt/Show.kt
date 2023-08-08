package com.example.sqlkt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Adapter
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.sqlkt.Sql.DbHelper
import com.example.sqlkt.Sql.User

class Show : AppCompatActivity() {

    lateinit var list:ListView

    lateinit var userList:List<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        refresh()
        registerForContextMenu(list)




    }

    fun refresh(){
        val db = DbHelper(this)
        list=findViewById(R.id.list_item)
        userList = db.getUsers()
        val adapter = MyAdapter(this, userList)
        list.adapter = adapter
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Menu") // Set the title of the context menu
        menu?.add(0,0,0,"Edit")
        menu?.add(0,1,0,"Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val acmi:AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo
        val position=acmi.position
        when(item.itemId){
            0-> {
                val i =Intent(this,Update::class.java)
                i.putExtra("t1",userList[position].Name)
                i.putExtra("t2",userList[position].Password)
                i.putExtra("id",userList[position].ID)
                startActivity(i)
                finish()
            }
            1-> {
                val db = DbHelper(this)
                db.delete(userList[position].ID)
                refresh()
            }
        }
        return super.onContextItemSelected(item)
    }


}

class MyAdapter(val context: Context,val list:List<User>):BaseAdapter(){
    override fun getCount(): Int {  return list.size }

    override fun getItem(p0: Int): Any { return list[p0] }

    override fun getItemId(p0: Int): Long {return p0.toLong()}

    override fun getView(i: Int, view: View?, parent: ViewGroup?): View {
       val view=LayoutInflater.from(context).inflate(R.layout.adapter,parent,false)
        val txt1=view.findViewById<TextView>(R.id.textView)
        val txt2=view.findViewById<TextView>(R.id.textView2)
        txt1.text=list[i].Name
        txt2.text=list[i].Password
        return view
    }

}

