package com.example.circlecircledotdot

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import java.util.Random


class DisplayFragment : Fragment() {

    companion object {
        private const val COLOR_KEY = "color"

        fun newInstance(color: Int): DisplayFragment {
            val fragment = DisplayFragment()
            val args = Bundle()
            args.putInt(COLOR_KEY, color)
            fragment.arguments = args
            return fragment
        }
    }

    private var color: Int = Color.BLACK // Default color if no argument is provided

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display, container, false)
        val surfaceView = view.findViewById<SurfaceView>(R.id.surfaceView)
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                // Start drawing on surface created
                drawCircle(holder)
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                // Respond to surface changes if needed
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                // Handle surface destruction if needed
            }
        })
        return view
    }

    private fun drawCircle(holder: SurfaceHolder) {
        val canvas: Canvas? = holder.lockCanvas()
        if (canvas != null) {
            canvas.drawColor(Color.WHITE) // Clear canvas
            val paint = Paint().apply {
                color = this@DisplayFragment.color
                isAntiAlias = true
            }
// X 1.05f längst höger 30f längst vänster
// y 1.03f = botten 20 f högst

            val random= Random()
            val screenWidth = canvas.width.toFloat()
            val screenHeight = canvas.height.toFloat()
            val centerX = random.nextFloat()*screenWidth
            val centerY = random.nextFloat()*screenHeight

            val radius = 50f
//            val centerX = canvas.width / 20f
//            val centerY = canvas.height / 30f
            canvas.drawCircle(centerX, centerY, radius, paint)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            color = it.getInt(COLOR_KEY)
        }
    }
}
