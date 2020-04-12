package com.codename1.demos.restaurant

import com.codename1.components.SpanLabel
import com.codename1.ui.CN.*
import com.codename1.ui.Container
import com.codename1.ui.Form
import com.codename1.ui.Label
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.*
import com.codename1.ui.util.Resources

fun showMenuScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.uiid = "MenuScreen"
    form.toolbar.hideToolbar()
    form.isSafeArea = true
    form.transitionOutAnimator = CommonTransitions.createFade(800)
    form.add(NORTH, getToolbar(theme, "Menu").wrapIntoBorders(theme, top = false))
    form.add(CENTER, getMenuContents(theme))
    form.show()
}

fun getMenuContents(res: Resources): Container {
     val contents = Container(getResponsiveGridLayout())
    for (menuItem in menuList) {
        val menuRow = getMenuCardNew(menuItem, res)
        contents.addComponent(menuRow)
    }
    //val decoratedContents = contents.wrapIntoBorders(res, top = false)
    contents.isScrollableY = true
    return contents
}

fun getMenuCardNew(menuItem: RestaurantMenuItem, res: Resources): Container {
    val menuCard = Container(BorderLayout())
    menuCard.responsiveUIId = "MenuCard"
    val foodInfo = Container(BoxLayout.y())
    foodInfo.responsiveUIId = "FoodInfo"
    val foodTitle = SpanLabel(menuItem.title.toUpperCase())
    foodTitle.textComponent.responsiveUIId ="FoodTitle"
    val foodDescription = SpanLabel(menuItem.description.ellipseString(35))
    foodDescription.textComponent.responsiveUIId = "FoodDescription"
    foodInfo.add(foodTitle)
    foodInfo.add(foodDescription)
    menuCard.add(WEST, foodInfo)

    val priceLabel = Label("$10")
    priceLabel.responsiveUIId = "MenuCardPrice"
    val badgeWrapper = FlowLayout.encloseIn(priceLabel)


    val menuCardWhole = LayeredLayout.encloseIn(menuCard, badgeWrapper)
    menuCardWhole.responsiveUIId = "MenuCardWhole"

    var cardToReturn = menuCardWhole
    if (menuItem.imageName != "") {
        val mobileWidth = if(isTablet()) 250 else 500
        val image = Container().add(res.getImage(menuItem.imageName).mobileWidth(mobileWidth))
        image.responsiveUIId = "MenuCardImage"
        cardToReturn = LayeredLayout.encloseIn(menuCardWhole, FlowLayout.encloseRightMiddle(image))
    }
    cardToReturn.onClick {
        showMenuDetails(res, menuItem)
    }
    return cardToReturn
}

data class RestaurantMenuItem(
        val title: String,
        val description: String,
        val imageName: String = ""
)

val menuList: List<RestaurantMenuItem> = listOf(
        RestaurantMenuItem("Pizza Margherata", "Amazing pizza chicken pieces with tanduri ", "pizza.png"),
        RestaurantMenuItem("Sea Food", "Delicious Fish with Tomato sauce very red hot. This is the best sea food in Nepal I would love to be in this next party lorem ipsum ", "seafood.png"),
        RestaurantMenuItem("Momo", "Delicious Pork Momo with Tomato sauce very red hot", "coffee.png"),
        RestaurantMenuItem("Thakali Food", "Best thakali food in the town", "sausage.png"),
        RestaurantMenuItem("Sausage", "Chicken sausage, made natually at himalayas' yaks intestine", "salad.png")
)

fun String.ellipseString(size: Int): String {
    val stringToProcess = this + "\n"
    if (stringToProcess.length > size) {
        var newString = ""
        newString = stringToProcess.substring(0, size)
        newString += "..."
        return newString
    }
    return this
}
