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
    val backButton = Button();
    val backArrowIcon = FontImage
            .createMaterial(FontImage.MATERIAL_ARROW_BACK, "WhiteIcon", 6f)
            .toImage()
    backButton.icon = backArrowIcon
    backButton.addActionListener {
        showMenuScreen(theme)
    }

    form.add(NORTH, getToolbar(
            backButton,
            Label("Preview".toUpperCase(), "SplashLabelMini"),
            Container().add(theme.getImage("logo.png"))))

    var itemDetailsInfo = Container(BorderLayout()).add(CENTER,getMenuDetails(theme,item))

    val goBackButton = Button("Go Back".toUpperCase(), "FoodDetailsGoBack")
    goBackButton.addActionListener {
        showMenuScreen(theme)
    }
    itemDetailsInfo.add(SOUTH,goBackButton)
    if (item.imageName != "") {
        val image = Container().add(theme.getImage(item.imageName).scaledWidth(450))
        image.uiid = "MenuCardImage"
        itemDetailsInfo = LayeredLayout.encloseIn(itemDetailsInfo, FlowLayout.encloseCenter(image))
    }
    form.add(CENTER,itemDetailsInfo)
    form.show()
}

fun getMenuDetails(theme: Resources, item: RestaurantMenuItem): Container {
    val dashboarderContainer = Container(BoxLayout.y());
    dashboarderContainer.uiid ="ItemDetailsInfo"

    val foodDetails = Container(BoxLayout.y());
    val priceRow = Container(BoxLayout.xCenter())
    val priceRowContent = Container(BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER))
    priceRowContent.add(CENTER,Label("$5","ItemPrice"))
    priceRowContent.add(EAST, getGreenLine())
    priceRowContent.add(WEST, getGreenLine())
    priceRow.add(priceRowContent)
    dashboarderContainer.add(priceRow)
    dashboarderContainer.add(SpanLabel(item.title.toUpperCase(),"FoodDetailsTitle"))
    dashboarderContainer.add(SpanLabel(item.description,"FoodDetailsDescription"))
    foodDetails.add(dashboarderContainer)
    return Container(BoxLayout.y()).add(foodDetails)
}

