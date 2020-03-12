package com.codename1.demos.restaurant

import com.codename1.ui.*
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.util.Resources

fun showSplashScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.uiid = "SplashScreen"
    val bookTableButton = Button("Book A table", "SplashButton")
    form.transitionOutAnimator = CommonTransitions.createFade(800)

    val splashTexts = Container(BoxLayout.yCenter())
    val splashScreenName = Label("Ratatouille".toUpperCase(), "SplashLabel")
    val splashScreenDescription = Label("Cucina Italiana".toUpperCase(), "SplashLabelMini")
    splashTexts.add(splashScreenName).add(splashScreenDescription)

    val container: Container = Container(BorderLayout())
            .add(CN.NORTH, splashTexts)

    val contents = Container(BoxLayout.yCenter())
    contents.add(container)


    val wholeScreen = Container(BorderLayout())
    wholeScreen.add(BorderLayout.CENTER, contents)
    wholeScreen.add(BorderLayout.SOUTH, bookTableButton)

    bookTableButton.addActionListener {
        contents.setShouldCalcPreferredSize(true)
        contents.layout = BoxLayout.y()
        contents.animateLayoutAndWait(500)
        showBookScreen(theme)
    }
    form.add(BorderLayout.CENTER, wholeScreen)
    form.show()
}
