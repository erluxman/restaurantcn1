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
    form.uiid = "MenuScreen1"
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

    val goBackButton = Button("Go Back".toUpperCase(), "GoBackButton")
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
    val greenLine = getGreenLine()
    greenLine.uiid = "GreenLine"
    val greenLine1 = Container()
    greenLine1.uiid = "GreenLine"
    greenLine1.add(Label("       ","GreenLineText"))
    priceRow.add(greenLine).add(Label("$5","ItemPrice")).add(greenLine1)
    dashboarderContainer.add(priceRow)
    dashboarderContainer.add(SpanLabel(item.title.toUpperCase(),"FoodDetailsTitle"))
    dashboarderContainer.add(SpanLabel(item.description,"FoodDetailsDescription"))
    foodDetails.add(dashboarderContainer)
    print("Printing new component")
    foodDetails.add( object : Component() {
        override fun paint(g: Graphics) { // red color
            g.color = 0xffffff
            // paint the screen in red
            print("printing rectangle properties  X :$x Y:$y Width : $width Height : $height")
            g.fillRect(x, y, width, height)
            // draw hi world in white text at the top left corner of the screen
            g.color = 0xffffff
            g.drawString("Hi World", x, y)
        }
    })
    foodDetails.add(Label("Namaste everywone","FoodDetailsDescription"))
    return Container(BoxLayout.y()).add(foodDetails)
}

