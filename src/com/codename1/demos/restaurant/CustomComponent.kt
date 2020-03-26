package com.codename1.demos.restaurant

import com.codename1.ui.Component
import com.codename1.ui.Form
import com.codename1.ui.Graphics
import com.codename1.ui.geom.Dimension
import com.codename1.ui.layouts.BorderLayout

fun showCustomForm() {
    val hi = Form("", BorderLayout())
    hi.add(BorderLayout.CENTER, getGreenLine())
    hi.show()
}

fun getGreenLine(): Component {
    return object : Component() {
        override fun paint(g: Graphics) {
            println("Graphics Printing starts")
            g.color = 0x00ff00
            g.fillRect(x, y, width, height)
        }

        override fun calcPreferredSize(): Dimension {
            return Dimension(1, 20)
        }
    }
}