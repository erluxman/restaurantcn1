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

    val splashScreenName = Label("Ratatouille".toUpperCase(), "AppTitleLabel")
    val splashScreenDescription = Label("Cucina Italiana".toUpperCase(), "AppTitleLabelMini")
    val orderScreenTitle = Container(BoxLayout.yCenter())
    orderScreenTitle.add(splashScreenName).add(splashScreenDescription)

    val bookATable = Label("Book A Table".toUpperCase(), "PickerTitle")
    val selectDateLabel = Label(" Date & Time".toUpperCase(), "PickerLabel")
    selectDateLabel.addLeadingIcon(FontImage.MATERIAL_CALENDAR_TODAY)
    val datePicker = PickerComponent.createDateTime(Date())
    val selectTimeLabel = Label(" Select Duration".toUpperCase(), "PickerLabel")
    selectTimeLabel.addLeadingIcon(FontImage.MATERIAL_SCHEDULE)
    val timePicker = PickerComponent.createDurationHoursMinutes(1,30)
    val selectPartySizeLabel = Label(" Party Size".toUpperCase(), "PickerLabel")
    selectPartySizeLabel.addLeadingIcon(FontImage.MATERIAL_PEOPLE)
    val contents = Container(BoxLayout.y())

    val partySizePicker = PickerComponent.createStrings("0-5 persons", "5-10 persons", "10-50 persons", "50+ persons")
    val pickers = Container(BoxLayout.y())
            .add(selectDateLabel)
            .add(datePicker.asDropDown(theme))
            .add(selectTimeLabel)
            .add(timePicker.asDropDown(theme))
            .add(selectPartySizeLabel)
            .add(partySizePicker.asDropDown(theme))

    pickers.style.setPaddingUnitRight(Style.UNIT_TYPE_SCREEN_PERCENTAGE)
    pickers.style.setPaddingRight(40f)

    contents.add(orderScreenTitle)
            .add(bookATable)
            .add(pickers)

    val wholeScreen = Container(BorderLayout())
    wholeScreen.add(BorderLayout.CENTER, contents)

    val confirmReservation = getDecoratedButton("Book Reservation".toUpperCase(),theme)
    confirmReservation.uiid = "RedPillButton"
    confirmReservation.onClick {
        showMenuScreen(theme)
    }

    val goBackButton = Button("Go Back")
    goBackButton.uiid = "BookScreenGoBack";
    goBackButton.addActionListener {
        showSplashScreen(theme)
    }
    val buttons = Container(BoxLayout.y());
    buttons.add(confirmReservation).add(goBackButton)
    form.add(BorderLayout.CENTER, wholeScreen)
    form.add(BorderLayout.NORTH, Container().wrapIntoBorders(theme,bottom = false))
    form.add(BorderLayout.SOUTH, buttons.wrapIntoBorders(theme,top = false))
    form.show()
}
