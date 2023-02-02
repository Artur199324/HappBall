package com.iugome.mytesttask.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.iugome.mytesttask.di.app.App
import com.iugome.mytesttask.databinding.ActivityMainBinding
import com.iugome.mytesttask.domain.models.UnsplashModel
import com.iugome.mytesttask.presentation.viewModel.MainViewModel
import com.iugome.mytesttask.presentation.viewModel.MainViewModelFactory
import com.iugome.mytesttask.presentation.adapter.UnsplashAdaptar
import com.iugome.mytesttask.presentation.adapter.listener
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(),UnsplashAdaptar.OnItemClickListener{
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var arrayUnsplashModel:ArrayList<UnsplashModel>
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private var updatesJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        (applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this,mainViewModelFactory)[MainViewModel::class.java]
        activityMainBinding.unsplashRecyclerView.layoutManager = LinearLayoutManager(this)
        updatesJob =  lifecycleScope.launch {
            arrayUnsplashModel = viewModel.loadImage()
            if (arrayUnsplashModel[0].id != ""){
                val unsplashAdaptar = UnsplashAdaptar(this@MainActivity,arrayUnsplashModel)
                activityMainBinding.unsplashRecyclerView.adapter = unsplashAdaptar
                listener = this@MainActivity
            }else{
                Toast.makeText(this@MainActivity,"Check internet connection",Toast.LENGTH_LONG).show()
            }
        }

        activityMainBinding.imageViewSearch.setOnClickListener {
            val text = activityMainBinding.editTextTSearch.text
            if (text.isNotEmpty()) {
                updatesJob?.cancel()
                updatesJob =  lifecycleScope.launch {
                    arrayUnsplashModel = viewModel.loadSerch(text.toString())
                    try {
                    if (arrayUnsplashModel[0].id != ""){
                        val unsplashAdaptar = UnsplashAdaptar(this@MainActivity,arrayUnsplashModel)
                        activityMainBinding.unsplashRecyclerView.adapter = unsplashAdaptar
                        listener = this@MainActivity
                    }else{
                        Toast.makeText(this@MainActivity,"Check internet connection",Toast.LENGTH_LONG).show()
                    }
                }catch (e:Exception){
                        Toast.makeText(this@MainActivity,"Enter your request in English",Toast.LENGTH_LONG).show()
                }
                }
            } else {
                Toast.makeText(this, "You can see the value in the search box", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(applicationContext, DetailedActivity::class.java)
        intent.putExtra("createdAt",arrayUnsplashModel[position].createdAt)
        intent.putExtra("likes",arrayUnsplashModel[position].likes)
        intent.putExtra("linkPhoto",arrayUnsplashModel[position].linkPhoto)
        intent.putExtra("name",arrayUnsplashModel[position].name)
        intent.putExtra("id",arrayUnsplashModel[position].id)
        intent.putExtra("bak","1")
        startActivity(intent)
        finish()
    }
}