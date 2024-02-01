package com.capgemini.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import layout.NewsAdapter

class MainActivity : AppCompatActivity() {
    lateinit var rView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView=findViewById(R.id.rView)
        rView.layoutManager=LinearLayoutManager(this)


        CoroutineScope(Dispatchers.Default).launch{ //since we hv used suspend
       val result= NewsInterface.getInstance()
           .getTopHeadlines("in","3f0b148b09c14b508d92a1e3195bf149")
        Log.d("MainActivity","No of articles: ${result.articles.size}")

        withContext(Dispatchers.Main){
            rView.adapter=NewsAdapter(result.articles){
                if(result.articles[it].url!=null){
                val intent=Intent(Intent.ACTION_VIEW,Uri.parse(result.articles[it].url))
                    startActivity(intent)

            }
        }
        }
    }
}}