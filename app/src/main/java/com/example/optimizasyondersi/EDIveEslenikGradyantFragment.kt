package com.example.optimizasyondersi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.optimizasyondersi.databinding.FragmentEDIveEslenikGradyantBinding

class EDIveEslenikGradyantFragment : Fragment() {
    private lateinit var binding: FragmentEDIveEslenikGradyantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentEDIveEslenikGradyantBinding.inflate(inflater, container, false)

        binding.buttonEDI.setOnClickListener {
            var algoritma = EDIveEslenikGradyantAlgoritmaları(
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

        binding.buttonEG.setOnClickListener {
            var algoritma = EDIveEslenikGradyantAlgoritmaları(
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
                Toast.makeText(requireContext(),"Sonsuz iterasyon oluştu!!!", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

}