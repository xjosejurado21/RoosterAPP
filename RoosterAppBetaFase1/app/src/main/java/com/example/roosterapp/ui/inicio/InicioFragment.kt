package com.example.roosterapp.ui.inicio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.roosterapp.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configurar clics en los CardView para redirigir a las páginas web
        setupCardViews()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupCardViews() {
        // Configurar clics en el primer evento
        binding.firstEventCardView.setOnClickListener {
            openWebPage("https://www.rooster.com/evento1")
        }

        // Configurar clics en el segundo evento
        binding.secondEventCardView.setOnClickListener {
            openWebPage("https://www.rooster.com/evento2")
        }

        // Configurar clics en el primer merchandising
        binding.firstMerchandiseCardView.setOnClickListener {
            openWebPage("https://www.rooster.com/merchandising1")
        }

        // Configurar clics en el segundo merchandising
        binding.secondMerchandiseCardView.setOnClickListener {
            openWebPage("https://www.rooster.com/merchandising2")
        }

        // Configurar clics en el tercer merchandising
        binding.thirdMerchandiseCardView.setOnClickListener {
            openWebPage("https://www.rooster.com/merchandising3")
        }
    }

    private fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}

