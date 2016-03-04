package com.seniorzhai.kotlindemo

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text.text = "hello kotlin"
        text.setOnClickListener {
            toast("Click text")
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyHolder();
        toast("this is message!!!")
    }

    class MyHolder : RecyclerView.Adapter<MyHolder.ViewHolder>() {
        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            holder?.textView!!.text = "Number$position" // 类似swift \()
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
