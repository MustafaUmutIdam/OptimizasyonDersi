package com.example.optimizasyondersi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.optimizasyondersi.databinding.FragmentAnaSayfaBinding

class AnaSayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnaSayfaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnaSayfaBinding.inflate(inflater, container, false)

        binding.buttonEDIveEslenik.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.anaSayfaToEDIveEslenik)
        }

        binding.buttonIsilIslem.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_anaSayfaFragment_to_isilIslemFragment)
        }

        return binding.root
    }

}