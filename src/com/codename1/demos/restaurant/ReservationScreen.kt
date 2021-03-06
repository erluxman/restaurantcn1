package com.codename1.demos.restaurant

import com.codename1.ui.*
import com.codename1.ui.CN.isTablet
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.layouts.GridLayout
import com.codename1.ui.plaf.Style
import com.codename1.ui.util.Resources
import java.util.*

fun showBookScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.responsiveUIId = "BookScreen"
    form.toolbar.hideToolbar()
    form.isSafeArea = true
    form.transitionOutAnimator = CommonTransitions.createFade(800)

    val fullLogo = Container(BoxLayout.xCenter()).add(theme.getImage("logofull.png")
            .mobileWidth(800))
    fullLogo.responsiveUIId = "AppFullLogo"
    val bookATable = Label("Book A Table".toUpperCase())
    bookATable.responsiveUIId = "PickerTitle"
    val selectDateLabel = Label(" Date & Time".toUpperCase())
    selectDateLabel.responsiveUIId = "PickerLabel"
    selectDateLabel.addLeadingIcon(FontImage.MATERIAL_CALENDAR_TODAY)
    val datePicker = PickerComponent.createDateTime(Date())
    val selectTimeLabel = Label(" Select Duration".toUpperCase())
    selectTimeLabel.responsiveUIId = "PickerLabel"
    selectTimeLabel.addLeadingIcon(FontImage.MATERIAL_SCHEDULE)
    val timePicker = PickerComponent.createDurationHoursMinutes(1, 30)
    val selectPartySizeLabel = Label(" Party Size".toUpperCase())
    selectPartySizeLabel.responsiveUIId = "PickerLabel"
    selectPartySizeLabel.addLeadingIcon(FontImage.MATERIAL_PEOPLE)
    val contents = Container(BoxLayout.y())

    val partySizePicker = PickerComponent.createStrings("0-5 persons", "5-10 persons", "10-50 persons", "50+ persons")
    val isTablet = isTablet();
    println("\nIs tablet : $isTablet")
    val totalGridItems = if (isTablet) 2 else 1
    val layout = if (totalGridItems > 1) GridLayout(totalGridItems, totalGridItems) else BoxLayout.y()
    println("Total Grid Items : $totalGridItems")
    println("Layout : $layout")
    val pickers =
            Container(BoxLayout.y())
                    .add(Container(layout)
            .add(Container(BoxLayout.y())
                    .add(selectDateLabel)
                    .add(datePicker.asDropDown(theme)))

            .add(Container(BoxLayout.y())
                    .add(selectTimeLabel)
                    .add(timePicker.asDropDown(theme))))

            .add(Container(BoxLayout.y())
                    .add(selectPartySizeLabel)
                    .add(partySizePicker.asDropDown(theme)))
    pickers.responsiveUIId = "PickersGroup"
    //pickers.layout = if(isTablet()) GridLayout(2) else BoxLayout.y()

    pickers.style.setPaddingUnitRight(Style.UNIT_TYPE_SCREEN_PERCENTAGE)
    pickers.style.setPaddingRight(if(isTablet)100f else 40f)

    contents.add(fullLogo)
            .add(bookATable)
            .add(pickers)

    val wholeScreen = Container(BorderLayout())
    wholeScreen.add(BorderLayout.CENTER, contents)

    val confirmReservation = getDecoratedButton("Book Reservation".toUpperCase(), theme)
    confirmReservation.responsiveUIId = "RedPillButton"
    confirmReservation.onClick {
        showMenuScreen(theme)
    }

    val goBackButton = Button("Go Back")
    goBackButton.responsiveUIId = "BookScreenGoBack";
    goBackButton.addActionListener {
        showSplashScreen(theme)
    }
    val buttons = Container(BoxLayout.y());
    buttons.add(confirmReservation).add(goBackButton)
    form.add(BorderLayout.CENTER, wholeScreen)
    form.add(BorderLayout.NORTH, Container().wrapIntoBorders(theme, bottom = false))
    form.add(BorderLayout.SOUTH, buttons.wrapIntoBorders(theme, top = false))
    form.show()
}
