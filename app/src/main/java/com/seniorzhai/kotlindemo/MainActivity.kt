package com.seniorzhai.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text.text = "hello kotlin"
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyHolder();
    }

    class MyHolder : RecyclerView.Adapter<MyHolder.ViewHolder>() {
        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            holder?.textView!!.text = "1111"
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder? {
            return ViewHolder(TextView(p0?.context))
        }

        class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    }
}
