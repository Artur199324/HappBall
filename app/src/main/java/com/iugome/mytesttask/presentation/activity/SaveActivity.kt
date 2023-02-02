package com.iugome.mytesttask.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.iugome.mytesttask.databinding.ActivitySaveBinding
import com.iugome.mytesttask.di.app.App
import com.iugome.mytesttask.domain.models.UnsplashModel
import com.iugome.mytesttask.presentation.adapter.UnsplashAdaptar
import com.iugome.mytesttask.presentation.adapter.listener
import com.iugome.mytesttask.presentation.viewModel.DetailedViewModel
import com.iugome.mytesttask.presentation.viewModel.SaveViewModel
import com.iugome.mytesttask.presentation.viewModel.SaveViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveActivity : AppCompatActivity(), UnsplashAdaptar.OnItemClickListener {
    lateinit var binding: ActivitySaveBinding
    lateinit var viewModel: SaveViewModel
    lateinit var arrayUnsplashModel: ArrayList<UnsplashModel>
    private var updatesJob: Job? = null

    @Inject
    lateinit var saveViewModelFactory: SaveViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arrayUnsplashModel = ArrayList()
        (applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, saveViewModelFactory)[SaveViewModel::class.java]
        binding.unsplashRecyclerViewSave.layoutManager = LinearLayoutManager(this)
        updatesJob = lifecycleScope.launch {
            Log.d("weq",viewModel.getDataBase().size.toString())
            if (viewModel.getDataBase().isNotEmpty()) {
                Log.d("weq","2222")
                for (i in 0 until viewModel.getDataBase().size) {
                    Log.d("weq","aaaaa")
                    arrayUnsplashModel.add(
                        UnsplashModel(
                            viewModel.getDataBase()[i].idUnsplash,
                            viewModel.getDataBase()[i].createdAt,
                            viewModel.getDataBase()[i].likes,
                            viewModel.getDataBase()[i].linkPhoto,
                            viewModel.getDataBase()[i].name
                        )
                    )
                }
                val unsplashAdaptar = UnsplashAdaptar(this@SaveActivity, arrayUnsplashModel)
                binding.unsplashRecyclerViewSave.adapter = unsplashAdaptar
                listener = this@SaveActivity

            } else {
                binding.unsplashRecyclerViewSave.visibility = View.INVISIBLE
                binding.textView.visibility = View.VISIBLE
                Log.d("weq","3333")
            }
        }
    }

    override fun onStop() {
        updatesJob?.cancel()
        super.onStop()

    }

    override fun onDestroy() {
        updatesJob?.cancel()
        super.onDestroy()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(applicationContext, DetailedActivity::class.java)
        intent.putExtra("createdAt", arrayUnsplashModel[position].createdAt)
        intent.putExtra("likes", arrayUnsplashModel[position].likes)
        intent.putExtra("linkPhoto", arrayUnsplashModel[position].linkPhoto)
        intent.putExtra("name", arrayUnsplashModel[position].name)
        intent.putExtra("id", arrayUnsplashModel[position].id)
        intent.putExtra("bak","2")
        startActivity(intent)
        finish()
    }
}