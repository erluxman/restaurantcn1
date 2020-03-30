package com.codename1.demos.restaurant

import com.codename1.components.SpanLabel
import com.codename1.ui.*
import com.codename1.ui.CN.*
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.*
import com.codename1.ui.util.Resources

fun showMenuScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.uiid = "MenuScreen"
    form.toolbar.hideToolbar()
    form.transitionOutAnimator = CommonTransitions.createFade(800)
    form.add(NORTH, getToolbar(theme,"Menu"))
    form.add(CENTER, getMenuContents(theme))
    form.show()
}

fun getMenuContents(res: Resources): Container {
    val selection = Container(BoxLayout.y())
    for (menuItem in menuList) {
        val menuRow = getMenuCard(menuItem, res)
        selection.addComponent(menuRow)
    }
    selection.isScrollableY = true
    return selection
}

fun getMenuCard(menuItem: RestaurantMenuItem, res: Resources): Container {
    var menuCard = Container(BorderLayout(), "MenuCard")
    val foodInfo = Container(BoxLayout.y(), "FoodInfo")
    foodInfo.add(SpanLabel(menuItem.title.toUpperCase(), "MenuCardItemTitle"))
    foodInfo.add(SpanLabel(menuItem.description, "MenuCardItemDescription"))
    menuCard.add(WEST, foodInfo)

    val badgeWrapper = FlowLayout.encloseIn(Label("$10", "MenuCardPrice"))
    //badgeWrapper.uiid = "Wrapper"
    menuCard = LayeredLayout.encloseIn(menuCard, badgeWrapper)


    if (menuItem.imageName != "") {
        val image = Container().add(res.getImage(menuItem.imageName).scaledWidth(550))
        image.uiid = "MenuCardImage"
        menuCard = LayeredLayout.encloseIn(menuCard, FlowLayout.encloseRightMiddle(image))
    }


    menuCard.onClick {
        showMenuDetails(res, menuItem)
    }
    return menuCard
}

data class RestaurantMenuItem(
        val title: String,
        val description: String,
        val imageName: String = ""
)

val menuList: List<RestaurantMenuItem> = listOf(
        RestaurantMenuItem("Margherata", "Amazing pizza chicken ", "pizza.png"),
        RestaurantMenuItem("Pizza Margherata", "Amazing pizza chicken pieces with tanduri ", "pizza.png"),
        RestaurantMenuItem("Sea Food", "Delicious Fish with Tomato sauce very red hot. This is the best sea food in Nepal I would love to be in this next party lorem ipsum ", "seafood.png"),
        RestaurantMenuItem("Momo", "Delicious Pork Momo with Tomato sauce very red hot", "momo.png"),
        RestaurantMenuItem("Thakali Food", "Best thakali food in the town", "thakali1.png"),
        RestaurantMenuItem("Sausage", "Chicken sausage, made natually at himalayas' yaks intestine"),
        RestaurantMenuItem("Cappuccino", "Milk, Ethiopian Coffee with himalaya yak milk"),
        RestaurantMenuItem("Cappuccino", "Milk, Ethiopian Coffee with himalaya yak milk"),
        RestaurantMenuItem("Burger", "Super soft buns with crunchy patties"),
        RestaurantMenuItem("Burger", "Super soft buns with crunchy patties")
)

