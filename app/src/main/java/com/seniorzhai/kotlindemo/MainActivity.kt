package com.seniorzhai.kotlindemo

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import com.seniorzhai.kotlindemo.Api.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

class MainActivity : BaseActivity(), View.OnClickListener {

    private var mApi: ApiService? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mApi = ApiService.create()
        text.text = "hello kotlin"
        text.setOnClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyHolder();
        toast("this is message!!!")
        button.setOnClickListener {
            mApi?.histroy()
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribeOn(Schedulers.io())
                    ?.subscribe (Action1 { h ->
                        (if (!h.error) {
                            toast("history.size=${h.results.size}")
                        } else {
                            Log.d("debug", Gson ().toJson(h));
                        })
                    }, Action1 { e -> toast("$e.message") })
        }

    }

    override fun onClick(view: View?) {
        toast("Click text")
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
