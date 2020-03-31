package com.codename1.demos.restaurant

import com.codename1.components.SpanLabel
import com.codename1.ui.CN.*
import com.codename1.ui.Container
import com.codename1.ui.Form
import com.codename1.ui.Label
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.layouts.FlowLayout
import com.codename1.ui.layouts.LayeredLayout
import com.codename1.ui.util.Resources

fun showMenuScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.uiid = "MenuScreen"
    form.toolbar.hideToolbar()
    form.transitionOutAnimator = CommonTransitions.createFade(800)
    form.add(NORTH, getToolbar(theme, "Menu").wrapIntoBorders(theme,top = false))
    form.add(CENTER, getMenuContents(theme))
    form.show()
}

fun getMenuContents(res: Resources): Container {
    val contents = Container(BoxLayout.y())
    for (menuItem in menuList) {
        val menuRow = getMenuCardNew(menuItem, res)
        contents.addComponent(menuRow)
    }
    val decoratedContents = contents.wrapIntoBorders(res,top = false)
    decoratedContents.isScrollableY = true
    return decoratedContents
}

fun getMenuCardNew(menuItem: RestaurantMenuItem, res: Resources): Container {
    val menuCard = Container(BorderLayout(), "MenuCard")
    val foodInfo = Container(BoxLayout.y(), "FoodInfo")
    val foodTitle = SpanLabel(menuItem.title.toUpperCase(), "FoodTitle")
    val foodDescription = SpanLabel(menuItem.description.ellipseString(35), "FoodDescription")
    foodInfo.add(foodTitle)
    foodInfo.add(foodDescription)

    menuCard.add(WEST, foodInfo)

    val badgeWrapper = FlowLayout.encloseIn(Label("$10", "MenuCardPrice"))

    val menuCardWhole = LayeredLayout.encloseIn(menuCard, badgeWrapper)
    menuCardWhole.uiid = "MenuCardWhole"

    var cardToReturn = menuCardWhole
    if (menuItem.imageName != "") {
        val image = Container().add(res.getImage(menuItem.imageName).scaledWidth(500))
        image.uiid = "MenuCardImage"
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
        RestaurantMenuItem("Sausage", "Chicken sausage, made natually at himalayas' yaks intestine","salad.png"),
        RestaurantMenuItem("Cappuccino", "Milk, Ethiopian Coffee with himalaya yak milk","kattiroll.png"),
        RestaurantMenuItem("Cappuccino", "Milk, Ethiopian Coffee with himalaya yak milk"),
        RestaurantMenuItem("Burger", "Super soft buns with crunchy patties"),
        RestaurantMenuItem("Burger", "Super soft buns with crunchy patties")
)

fun String.ellipseString(size: Int): String {
    val stringToProcess = this+"\n"
    if (stringToProcess.length > size) {
        var newString = ""
        newString = stringToProcess.substring(0, size)
        newString += "..."
        return newString
    }
    return this
}
