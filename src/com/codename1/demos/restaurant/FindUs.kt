package com.codename1.demos.restaurant

import com.codename1.components.SpanLabel
import com.codename1.ui.*
import com.codename1.ui.CN.*
import com.codename1.ui.animations.CommonTransitions
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.util.Resources

fun showFindUsScreen(theme: Resources) {
    val form = Form(BorderLayout())
    form.uiid = "MenuScreen"
    form.toolbar.hideToolbar()
    form.transitionOutAnimator = CommonTransitions.createFade(800)
    form.add(NORTH, getToolbar(theme, "Find Us").wrapIntoBorders(theme,top = false))
    form.add(CENTER, getFindUsScreenBody(theme))
    form.add(SOUTH, getNavigateButton(theme).wrapIntoBorders(theme,top = false))
    form.show()
}

fun getFindUsScreenBody(theme: Resources): Container {
    val container = Container(BoxLayout.y())
    container.add(getAddressAndPhone(theme))
    container.add(getMapImage(theme))
    return container
}

fun getAddressAndPhone(theme: Resources): Container {
    val fullContents = Container(BoxLayout.x(), "AddressCard")
    fullContents.add(getAddressCard(theme)).add(getPhoneCard())
    return fullContents
}

private fun getAddressCard(theme: Resources): Container {
    val container = Container(BoxLayout.x())

    val shopIcon = theme.getImage("address.png")
    val address = Container(BoxLayout.y())
            .add(Label("Ratatouille", "AddressCardText"))
            .add(Label("55 3rd Street", "AddressCardText"))
            .add(Label("Ny Ny 6666", "AddressCardText"))
    container.add(shopIcon).add(address)
    return container
}

private fun getPhoneCard(): Container {
    val container = Container(BoxLayout.x())
    container.uiid = "PhoneCard"
    val phoneIcon = FontImage
            .createMaterial(FontImage.MATERIAL_PHONE, "RedIcon", 6f)
            .toImage()
    container.add(phoneIcon).add(SpanLabel("+1-800-323-3233", "AddressCardText"))
    return container;
}

fun getMapImage(theme: Resources):Container{
    val width =Display.getInstance().displayWidth
    return Container().add(theme.getImage("map.png").scaledWidth(width))
}

fun getNavigateButton(theme: Resources): Container {
    val container = Container(BoxLayout.y())
    val bookTableButton = getDecoratedButton("Navigate",theme)
    bookTableButton.uiid ="FindUsPillButton"
    return container.add(bookTableButton)
}