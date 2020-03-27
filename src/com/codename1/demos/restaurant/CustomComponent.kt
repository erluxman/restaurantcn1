package com.codename1.demos.restaurant

import com.codename1.ui.Component
import com.codename1.ui.Container
import com.codename1.ui.Form
import com.codename1.ui.Graphics
import com.codename1.ui.geom.Dimension
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout

fun showCustomForm() {
    val hi = Form("", BorderLayout())
    hi.add(BorderLayout.CENTER, getGreenLine())
    hi.show()
}

fun getGreenLine(): Component {
    val greenLine =  object : Component() {
        override fun paint(g: Graphics) {
            println("Graphics Printing starts")
            g.color = 0x338833
            g.fillRect(x, y, width, height)
        }

        override fun calcPreferredSize(): Dimension {
            return Dimension(100, 5)
        }
    }
    val greenLineContainer = Container(BoxLayout.y()).add(Container(BoxLayout.xCenter()).add(greenLine))
    greenLineContainer.uiid = "GreenLine"

    return greenLineContainer
}