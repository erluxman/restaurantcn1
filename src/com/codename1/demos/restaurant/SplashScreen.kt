package com.codename1.demos.restaurant

import com.codename1.ui.Container
import com.codename1.ui.Form
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.layouts.FlowLayout
import com.codename1.ui.layouts.LayeredLayout
import com.codename1.ui.util.Resources

fun showSplashScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.responsiveUIId = "SplashScreen"
    form.toolbar.hideToolbar()
    form.isSafeArea = true
    form.transitionOutAnimator = CommonTransitions.createFade(800)

    val fullLogo = Container(BoxLayout.xCenter()).add(theme.getImage("logofull.png")
            .mobileWidth(800))
    fullLogo.uiid ="AppFullLogo"
    val contents = Container(BoxLayout.yCenter())
    contents.add(fullLogo)
    var decoratedBookTableButton = getDecoratedButton("Book A table".toUpperCase(), theme)
    decoratedBookTableButton.onClick {
        contents.setShouldCalcPreferredSize(true)
        contents.layout = BoxLayout.y()
        contents.animateLayoutAndWait(500)
        showBookScreen(theme)
    }

    val wholeScreen = Container(BorderLayout())
    wholeScreen.add(BorderLayout.CENTER, contents)
    decoratedBookTableButton.uiid = "SplashPillButton"
    decoratedBookTableButton = Container(BoxLayout.y()).add(decoratedBookTableButton)
    decoratedBookTableButton = FlowLayout.encloseCenterMiddle(decoratedBookTableButton)
    decoratedBookTableButton.uiid = "SplashButton"
    val finalContents = LayeredLayout.encloseIn(wholeScreen,decoratedBookTableButton )

    val topScreen = Container().wrapIntoBorders(theme, bottom = false)
    form.add(BorderLayout.NORTH,topScreen )
    form.add(BorderLayout.CENTER, finalContents)
    form.add(BorderLayout.SOUTH, Container().wrapIntoBorders(theme, bottom = false))
    form.show()
}
