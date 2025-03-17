package com.example.optimizasyondersi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.optimizasyondersi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.buttonEnDikInis.setOnClickListener {
            var algoritma = Algoritmalar(
                binding.inputTextx1.text.toString().toDouble(),
                binding.inputTextx2.text.toString().toDouble(),
                binding.inputTextx3.text.toString().toDouble(),
                binding.inputTextEpsilon.text.toString().toDouble(),
                binding.inputTextAdimBuyuklugu.text.toString().toDouble()
            )

            var listEDI = algoritma.minimizeEnDikInis()
            binding.textViewSonucx1.text = listEDI[0].toString()
            binding.textViewSonucx2.text = listEDI[1].toString()
            binding.textViewSonucx3.text = listEDI[2].toString()

            binding.textViewSonucIterasyon.text = listEDI[3].toString()
        }

        binding.buttonEslenikGradyant.setOnClickListener {
            var algoritma = Algoritmalar(
                binding.inputTextx1.text.toString().toDouble(),
                binding.inputTextx2.text.toString().toDouble(),
                binding.inputTextx3.text.toString().toDouble(),
                binding.inputTextEpsilon.text.toString().toDouble(),
                binding.inputTextAdimBuyuklugu.text.toString().toDouble()
            )
            var listEslenik =
                algoritma.minimizeEslenik()
            binding.textViewEGx1.text = listEslenik[0].toString()
            binding.textViewEGx2.text = listEslenik[1].toString()
            binding.textViewEGx3.text = listEslenik[2].toString()

            binding.textViewSonucIterasyonED.text = listEslenik[3].toString()

            if(listEslenik[3] == 10001.0){
                Toast.makeText(this,"Sonsuz iterasyon oluştu!!!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}