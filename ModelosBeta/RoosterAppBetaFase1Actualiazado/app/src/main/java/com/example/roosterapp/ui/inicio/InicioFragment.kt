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

        // Configurar clics en las vistas según sea necesario
        binding.firstEventCardview.setOnClickListener {
            // Acción cuando se hace clic en el primer CardView
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ejemplo.com"))
            startActivity(intent)
        }

        binding.secondEventCardview.setOnClickListener {
            // Acción cuando se hace clic en el segundo CardView
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ejemplo.com"))
            startActivity(intent)
        }

        binding.firstMerchandiseCardview.setOnClickListener {
            // Acción cuando se hace clic en el tercer CardView
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ejemplo.com"))
            startActivity(intent)
        }

        binding.secondMerchandiseCardview.setOnClickListener {
            // Acción cuando se hace clic en el cuarto CardView
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ejemplo.com"))
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


