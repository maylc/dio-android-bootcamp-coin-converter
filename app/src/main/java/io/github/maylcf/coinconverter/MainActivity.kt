package io.github.maylcf.coinconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.maylcf.coinconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}