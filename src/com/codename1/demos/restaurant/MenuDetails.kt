package com.codename1.demos.restaurant

import com.codename1.components.SpanLabel
import com.codename1.ui.*
import com.codename1.ui.CN.*
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.layouts.FlowLayout
import com.codename1.ui.layouts.LayeredLayout
import com.codename1.ui.util.Resources

fun showMenuDetails(theme: Resources, item: RestaurantMenuItem) {
    val form = Form(BorderLayout())
    form.transitionOutAnimator = CommonTransitions.createFade(800)
    form.uiid = "MenuScreen"
    form.toolbar.hideToolbar()
    form.isSafeArea = true
    val backButton = Button();
    val backArrowIcon = FontImage
            .createMaterial(FontImage.MATERIAL_ARROW_BACK, "WhiteIcon", 6f)
            .toImage()
    backButton.icon = backArrowIcon
    backButton.addActionListener {
        showMenuScreen(theme)
    }
    val logo = Container().add(theme.getImage("logo.png").scaledHeight(70))
    logo.uiid = "LogoIcon"

    form.add(NORTH, getToolbar(
            backButton, Label("Preview".toUpperCase(), "MenuTitleLabel"), logo))

    var itemDetailsInfo = Container(BorderLayout()).add(CENTER, getMenuDetails(theme, item))

    val goBackButton = getDecoratedButton("Go Back".toUpperCase(), theme)
    goBackButton.uiid = "RedPillButtonFoodDetails"
    goBackButton.onClick {
        showMenuScreen(theme)
    }
    itemDetailsInfo.add(SOUTH, goBackButton)
    if (item.imageName != "") {
        val image = Container().add(theme.getImage(item.imageName).scaledWidth(550))
        image.uiid = "MenuCardImage"
        itemDetailsInfo = LayeredLayout.encloseIn(itemDetailsInfo, FlowLayout.encloseCenter(image))
    }
    form.add(CENTER, itemDetailsInfo.wrapIntoBorders(theme, bottom = false))
    form.add(SOUTH, Container().wrapIntoBorders(theme, top = false))
    form.show()
}

fun getMenuDetails(theme: Resources, item: RestaurantMenuItem): Container {
    val dashboarderContainer = Container(BoxLayout.y());
    dashboarderContainer.uiid = "ItemDetailsInfo"

    val foodDetails = Container(BoxLayout.y());
    val priceRow = Container(BoxLayout.xCenter())
    val priceRowContent = Container(BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER))
    priceRowContent.add(CENTER, Label("$5", "ItemPrice"))
    priceRowContent.add(EAST, getGreenLine())
    priceRowContent.add(WEST, getGreenLine())
    priceRow.add(priceRowContent)
    dashboarderContainer.add(priceRow)
    dashboarderContainer.add(SpanLabel(item.title.toUpperCase(), "FoodDetailsTitle"))
    dashboarderContainer.add(SpanLabel(item.description, "FoodDetailsDescription"))
    foodDetails.add(dashboarderContainer)
    return Container(BoxLayout.y()).add(foodDetails)
}
