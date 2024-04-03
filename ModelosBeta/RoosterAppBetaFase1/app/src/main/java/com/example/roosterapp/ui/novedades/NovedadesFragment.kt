// Clase NovedadesFragment
package com.example.roosterapp.ui.novedades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roosterapp.databinding.FragmentNovedadesBinding

class NovedadesFragment : Fragment() {

    private var _binding:FragmentNovedadesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val novedadesViewModel =
            ViewModelProvider(this).get(NovedadesViewModel::class.java)

        _binding = FragmentNovedadesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNovedades
        novedadesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
