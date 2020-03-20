package com.codename1.demos.restaurant

import com.codename1.ui.CN
import com.codename1.ui.Container
import com.codename1.ui.Form
import com.codename1.ui.Label
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.util.Resources

fun showSplashScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.uiid = "SplashScreen"
    form.toolbar.hideToolbar()
    form.transitionOutAnimator = CommonTransitions.createFade(800)

    val splashTexts = Container(BoxLayout.yCenter())
    val splashScreenName = Label("Ratatouille".toUpperCase(), "SplashLabel")
    val splashScreenDescription = Label("Cucina Italiana".toUpperCase(), "SplashLabelMini")
    splashTexts.add(splashScreenName).add(splashScreenDescription)

    val container: Container = Container(BorderLayout())
            .add(CN.NORTH, splashTexts)

    val contents = Container(BoxLayout.yCenter())
    contents.add(container)
    val decoratedBookTableButton = getDecoratedButton("Book A table".toUpperCase(), theme)
    decoratedBookTableButton.uiid = "RedPillButton"
    decoratedBookTableButton.setOnClickListener {
        contents.setShouldCalcPreferredSize(true)
        contents.layout = BoxLayout.y()
        contents.animateLayoutAndWait(500)
        showBookScreen(theme)
    }

    val wholeScreen = Container(BorderLayout())
    wholeScreen.add(BorderLayout.CENTER, contents)
    wholeScreen.add(BorderLayout.SOUTH, decoratedBookTableButton)

    form.add(BorderLayout.CENTER, wholeScreen)
    form.show()
}
