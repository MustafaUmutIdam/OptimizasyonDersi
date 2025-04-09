package com.example.optimizasyondersi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optimizasyondersi.databinding.FragmentIsilIslemBinding

class IsilIslemFragment : Fragment() {
    private lateinit var binding: FragmentIsilIslemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIsilIslemBinding.inflate(layoutInflater, container, false)

        binding.buttonIsilIslem.setOnClickListener {
            var algoritma = IsilIslemAlgoritmasi(
                binding.inputTextx1.text.toString().toDouble(),
                binding.inputTextx2.text.toString().toDouble(),
                binding.inputTextAlt.text.toString().toDouble(),
                binding.inputTextUst.text.toString().toDouble(),
                binding.inputTextSicaklik.text.toString().toDouble(),
                binding.inputTextSoguma.text.toString().toDouble(),
                binding.inputTextIterasyon.text.toString().toInt()
            )
            var listIsilIslem = algoritma.minimizeEt()

            binding.textViewEnIyix1.text = listIsilIslem[0].toString()
            binding.textViewEnIyix2.text = listIsilIslem[1].toString()
            binding.textViewEnIyiDeger.text = listIsilIslem[2].toString()
        }

        return binding.root
    }

}