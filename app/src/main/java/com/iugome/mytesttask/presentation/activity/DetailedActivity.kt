package com.iugome.mytesttask.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.iugome.mytesttask.R
import com.iugome.mytesttask.di.app.App
import com.iugome.mytesttask.data.room.EntityUnsplash
import com.iugome.mytesttask.databinding.ActivityDetailedBinding
import com.iugome.mytesttask.presentation.viewModel.DetailedViewModel
import com.iugome.mytesttask.presentation.viewModel.DetailedViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailedActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailedBinding
    lateinit var viewModel: DetailedViewModel

    @Inject
    lateinit var detailedViewModelFactory: DetailedViewModelFactory
    var isSave = true
    var idSave = 0
    private var updatesJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, detailedViewModelFactory)[DetailedViewModel::class.java]
        Picasso.get().load(intent.getStringExtra("linkPhoto")).fit().centerInside()
            .into(binding.imageView)
        binding.textViewCreated.text = intent.getStringExtra("createdAt")
        binding.textViewName.text = intent.getStringExtra("name")
        binding.textViewlikes.text = intent.getStringExtra("likes")
        updatesJob = lifecycleScope.launch {

            if (viewModel.getDataBase().isEmpty()) {
                binding.buttonSave.text = resources.getText(R.string.save)
                isSave = true
            } else {
                for (i in 0 until viewModel.getDataBase().size) {
                    if (viewModel.getDataBase()[i].idUnsplash == intent.getStringExtra("id")) {
                        binding.buttonSave.text = resources.getText(R.string.delete)
                        idSave = i
                        Log.d("wew", idSave.toString())
                        isSave = false
                    } else {
                        binding.buttonSave.text = resources.getText(R.string.save)
                        isSave = true
                    }
                }
            }
        }
        binding.buttonSave.setOnClickListener {
            updatesJob?.cancel()
            updatesJob = lifecycleScope.launch {
                if (isSave) {
                    save()
                    isSave = false
                    binding.buttonSave.text = resources.getText(R.string.delete)
                } else {
                    delate(idSave)
                    isSave = true
                    binding.buttonSave.text = resources.getText(R.string.save)
                }
            }
        }

        binding.buttonShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, intent.getStringExtra("linkPhoto"))
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, resources.getString(R.string.share)))
        }
    }

    private suspend fun save() {

        viewModel.insertt(
            EntityUnsplash(
                intent.getStringExtra("id").toString(),
                intent.getStringExtra("createdAt").toString(),
                intent.getStringExtra("likes").toString(),
                intent.getStringExtra("linkPhoto").toString(),
                intent.getStringExtra("name").toString()
            )
        )
    }

    private suspend fun delate(i: Int) {
        viewModel.getDataBase()[i].id?.let { viewModel.delete(it) }
    }

    override fun onBackPressed() {
        if (intent.getStringExtra("bak").toString() == "1") {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(applicationContext, SaveActivity::class.java))
            finish()
        }
    }
}