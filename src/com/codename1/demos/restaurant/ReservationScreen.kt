package com.codename1.demos.restaurant

import com.codename1.ui.*
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.plaf.Style
import com.codename1.ui.util.Resources
import java.util.*

fun showBookScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.uiid = "BookScreen"
    form.toolbar.hideToolbar()
    form.transitionOutAnimator = CommonTransitions.createFade(800)

    val splashScreenName = Label("Ratatouille".toUpperCase(), "SplashLabel")
    val splashScreenDescription = Label("Cucina Italiana".toUpperCase(), "SplashLabelMini")
    val orderScreenTitle = Container(BoxLayout.yCenter())
    orderScreenTitle.add(splashScreenName).add(splashScreenDescription)

    val container: Container = Container(BorderLayout())
            .add(CN.NORTH, orderScreenTitle)

    val bookATable = Label("Book A Table".toUpperCase(), "PickerTitle")
    val selectDateLabel = Label("Select Date".toUpperCase(), "PickerLabel")
    val datePicker = PickerComponent.createDate(Date())
    val selectTimeLabel = Label("Select Time".toUpperCase(), "PickerLabel")
    val timePicker = PickerComponent.createTime(0)
    val selectPartySizeLabel = Label("Party Size".toUpperCase(), "PickerLabel")
    val contents = Container(BoxLayout.y())

    val partySizePicker = PickerComponent.createStrings("0-5 persons", "5-10 persons", "10-50 persons", "50+ persons")
    datePicker.uiid ="DropdownStyle"
    timePicker.uiid="DropdownStyle"
    timePicker.uiid="DropdownStyle"
    timePicker.style.fgColor = 0xfffff
    val pickers = Container(BoxLayout.y())
            .add(selectDateLabel)
            .add(datePicker)
            .add(selectTimeLabel)
            .add(timePicker)
            .add(selectPartySizeLabel)
            .add(partySizePicker)

    pickers.style.setPaddingUnitRight(Style.UNIT_TYPE_SCREEN_PERCENTAGE)
    pickers.style.setPaddingRight(40f)

    contents.add(container)
            .add(bookATable)
            .add(pickers)

    val wholeScreen = Container(BorderLayout())
    wholeScreen.add(BorderLayout.CENTER, contents)
    //wholeScreen.add(SOUTH, confirmReservation)

    val confirmReservation = getDecoratedButton("Book Reservation".toUpperCase(),theme)
    confirmReservation.uiid = "RedPillButton"
    confirmReservation.setOnClickListener {
        showMenuScreen(theme)
    }

    val goBackButton = Button("Go Back")
    goBackButton.uiid = "BookScreenGoBack";
    goBackButton.addActionListener {
        showSplashScreen(theme)
    }
    val buttons = Container(BoxLayout.y());
    buttons.add(confirmReservation).add(goBackButton)
    //wholeScreen.add(BorderLayout.SOUTH, buttons)
    form.add(BorderLayout.CENTER, wholeScreen)
    form.add(BorderLayout.SOUTH, buttons)
    // form.add(SOUTH, buttons)
    form.show()
}
