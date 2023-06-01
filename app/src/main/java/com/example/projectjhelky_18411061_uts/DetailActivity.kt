package com.example.projectjhelky_18411061_uts

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.projectjhelky_18411061_uts.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intss = intent
        val imageSrc = intss.getStringExtra("IMAGESRC")
        val imageTitle =intss.getStringExtra("IMAGETITLE")
        val imageDesc =intss.getStringExtra("IMAGEDESC")

        binding.imageTitle.text = imageTitle
        binding.imageDetail.loadImage(imageSrc)
        binding.imageDesc.text = imageDesc
    }
}