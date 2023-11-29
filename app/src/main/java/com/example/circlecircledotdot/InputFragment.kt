package com.example.circlecircledotdot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


import kotlin.random.Random

class InputFragment : Fragment() {

    companion object {
        fun newInstance() = InputFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCreate = view.findViewById<Button>(R.id.btn_create)

        btnCreate.setOnClickListener {
            val randomColor = getRandomColor()
            val displayFragment = DisplayFragment.newInstance(randomColor)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, displayFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun getRandomColor(): Int {
        val random = Random
        return android.graphics.Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )
    }
}
