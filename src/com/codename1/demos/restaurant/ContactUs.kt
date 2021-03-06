package com.codename1.demos.restaurant

import com.codename1.ui.CN.*
import com.codename1.ui.Container
import com.codename1.ui.Form
import com.codename1.ui.Label
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.layouts.GridLayout
import com.codename1.ui.util.Resources

fun showContactUsScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.responsiveUIId = "MenuScreen"
    form.toolbar.hideToolbar()
    form.isSafeArea = true
    form.transitionOutAnimator = CommonTransitions.createFade(800)
    form.add(NORTH, getToolbar(theme, "Contact Us"))
    form.add(CENTER, getContactUsScreenBody(theme))
    form.show()
}

fun getContactUsScreenBody(theme: Resources): Container {
    val container = Container(BoxLayout.y())
    container.add(getImageContainer(theme))
    container.add(getSchedule())
    container.add(getContact())
    val decoratedContainer = container.wrapIntoBorders(theme)
    decoratedContainer.isScrollableY = true
    return decoratedContainer
}

fun getImageContainer(theme: Resources): Container {
    val imageContainer = Container(BoxLayout.xCenter())
    imageContainer.add(theme.getImage("weareopen.png").scaledWidth(750))
    return imageContainer
}

fun getSchedule(): Container {
    val container = Container(BoxLayout.y())
    container.add(Label("Hours".toUpperCase()).withResponsiveId( "ItemTitle"))
    container.add(getScheduleContent())
    return container
}

fun getContact(): Container {
    val container = Container(BoxLayout.y())
    container.add(Label("Email Address".toUpperCase()).withResponsiveId("ItemTitle"))
    container.add(Label("erluxman@gmail.com").withResponsiveId("ShcheduleRowDashText"))

    container.add(Label("Address".toUpperCase()).withResponsiveId( "ItemTitle"))
    container.add(Label("Ratatouille").withResponsiveId( "ShcheduleRowDashText"))
    container.add(Label("55 3rd Street").withResponsiveId( "ShcheduleRowDashText"))
    container.add(Label("Ny Ny 6666").withResponsiveId( "ShcheduleRowDashText"))

    container.add(Label("Phone".toUpperCase()).withResponsiveId( "ItemTitle"))
    container.add(Label("+1-800-777-777").withResponsiveId( "ShcheduleRowDashText"))

    return container
}

fun getScheduleContent(): Container {
    val container = Container(GridLayout(7, 3))
    for (schedule in schedules) {
        container.add(Label(schedule.day.toUpperCase()).withResponsiveId( "ShcheduleRowText"))
        container.add(Label("--------").withResponsiveId( "ShcheduleRowDashText"))
        container.add(Label(schedule.schedule).withResponsiveId( "ShcheduleRowText"))
    }
    return container
}

data class RoutineModel(
        val day: String,
        val schedule: String
)

val schedules = listOf(
        RoutineModel("Sunday", "11AM - 11PM"),
        RoutineModel("Monday", "11AM - 11PM"),
        RoutineModel("Tuesday", "11AM - 11PM"),
        RoutineModel("Wednesday", "11AM - 11PM"),
        RoutineModel("Thursday", "11AM - 11PM"),
        RoutineModel("Friday", "11AM - 11PM"),
        RoutineModel("Saturday", "11AM - 11PM")
)