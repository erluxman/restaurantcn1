package com.codename1.demos.restaurant

import com.codename1.components.ScaleImageLabel
import com.codename1.ui.*
import com.codename1.ui.CN.*
import com.codename1.ui.layouts.BorderLayout
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.layouts.GridLayout
import com.codename1.ui.layouts.Layout
import com.codename1.ui.util.Resources

fun getToolbar(theme: Resources, titleString: String): Container {
    val hamburgerIcon = FontImage
            .createMaterial(FontImage.MATERIAL_MENU, "WhiteIcon", if (isTablet()) 10f else 6f)
            .toImage()
    val title = Label("   $titleString".toUpperCase())
    title.responsiveUIId = "MenuTitleLabel"
    val logo = Container().add(theme.getImage("logo.png").scaledHeight(70))
    logo.responsiveUIId = "LogoIcon"
    val menu = Button()
    menu.addActionListener {
        val sheet = Sheet(null, "")
        //sheet.preferredH = 1000
        sheet.layout = BorderLayout()
        sheet.responsiveUIId = "MenuSheet"
        sheet.position = CN.NORTH
        val g = GridLayout(2)
        g.isAutoFit = false
        val sheetContents = Container(getResponsiveGridLayout())
        sheetContents.responsiveUIId ="SheetContents"
        val titleRow = Container(BoxLayout.xCenter())
        titleRow.responsiveUIId = "MenuToolbar"
        val closeIcon = FontImage
                .createMaterial(FontImage.MATERIAL_CLOSE, "WhiteIcon", 6f)
                .toImage()
        val titleLogo = theme.getImage("logo.png").scaledHeight(70)
        val closeButton = Button()
        closeButton.icon = closeIcon
        sheet.hideBackButton()
        closeButton.addActionListener { sheet.back() }
        titleRow.add(titleLogo)

        val firstRow = Container(BorderLayout())
        val secondRow = Container(BorderLayout())
        firstRow.responsiveUIId ="MenuPairLeft"
        secondRow.responsiveUIId ="MenuPairRight"
        firstRow.add(CN.WEST, getMenuItem("Menu", theme.getImage("foodmenu.png"),
                ::showMenuScreen, theme))
        firstRow.add(CN.EAST, getMenuItem("Reservation", theme.getImage("reservation.png"),
                ::showBookScreen, theme))
        secondRow.add(CN.WEST, getMenuItem("Find Us", theme.getImage("findus.png"),
                ::showFindUsScreen, theme))
        secondRow.add(CN.EAST, getMenuItem("Contact Us", theme.getImage("contactus.png"),
                ::showContactUsScreen, theme))
        sheetContents.add(firstRow)
        sheetContents.add(secondRow)

        val actionBar = Container(BorderLayout())
        actionBar.add(CN.WEST, closeButton)
        actionBar.add(CN.CENTER, titleRow)
        actionBar.add(CN.EAST, Label("            "))
        sheet.add(CN.NORTH, actionBar)
        sheet.add(CN.CENTER, sheetContents.wrapIntoBorders(theme, top = false))
        sheet.show()

    }
    menu.icon = hamburgerIcon
    return getToolbar(menu, title, logo)
}

fun getMenuItem(title: String, icon: Image, actionListener: (Resources) -> Any, theme: Resources): Container {
    val menu = Container(BoxLayout.y())
    menu.responsiveUIId = "MenuTile"
    val iconButton = Button()
    val menuButton = Button()

    val isTablet = isTablet()
    val height = if(isTablet) 100 else 60
    iconButton.icon = icon.scaledHeight(height)
    menuButton.addActionListener {
        actionListener(theme)
    }
    menu.leadComponent = menuButton
    val menuTitle = Label(title.toUpperCase())
    menuTitle.responsiveUIId = "MenuTileLabel"
    menu.add(iconButton).add(menuTitle)
    return menu
}

fun getToolbar(left: Component, center: Component, right: Component): Container {
    val toolbar = Container(BorderLayout())
    toolbar.responsiveUIId = "MenuToolbar"
    toolbar.add(CN.WEST, left)
    toolbar.add(CN.EAST, right)
    toolbar.add(CN.CENTER, center)
    return toolbar
}

fun Container.onClick(listener: () -> Any) {
    val button = Button()
    button.addActionListener { listener() }
    this.leadComponent = button
}

fun getDecoratedButton(text: String, theme: Resources, textStyle: String = "PillButtonText"): Container {
    val decoratedBookTableButton = Container(BoxLayout.xCenter())
    val decRight = ScaleImageLabel(theme.getImage("decorationright.png").scaledHeight(36))
    val decLeft = ScaleImageLabel(theme.getImage("decorationleft.png").scaledHeight(36))
    val bookTableButton = Label(text.toUpperCase())
    bookTableButton.responsiveUIId = textStyle
    decoratedBookTableButton
            .add(decLeft)
            .add(bookTableButton)
            .add(decRight)
    return decoratedBookTableButton
}


fun Container.wrapIntoBorders(theme: Resources, top: Boolean = true, bottom: Boolean = true): Container {
    val imageName = if (isTablet()) "borderline_tab.png" else "borderline.png"
    val newContainer = Container(BoxLayout.y())
    val topBorder = theme.getImage(imageName).scaledWidth(getDisplayWidth())
    val bottomBorder = theme.getImage(imageName).scaledWidth(getDisplayWidth())
    if (top) newContainer.add(topBorder)
    newContainer.add(this)
    if (bottom) newContainer.add(bottomBorder)
    return newContainer;
}

fun Component.asDropDown(theme: Resources): Container {
    val dropDown = theme.getImage("dropdown.png").scaledWidth(50)
    return Container(BoxLayout.x()).add(this).add(dropDown)
}

fun Label.addLeadingIcon(icon: Char) {
    this.icon = FontImage
            .createMaterial(icon, "WhiteIcon", 3f)
            .toImage()
}

fun Image.mobileWidth(width: Int): Image {
    val standardWidth = 1200.toFloat()
    val displayWidth = getDisplayWidth().toFloat()
    val weightedWidth = ((displayWidth / standardWidth) * width.toFloat()).toInt()
    return this.scaledWidth(weightedWidth)
}

fun Image.mobileHeight(height: Int): Image {
    val standardHeight = 2000.toFloat()
    val displayHeight = getDisplayHeight().toFloat()
    val weightedHeight = ((displayHeight / standardHeight) * width.toFloat()).toInt()
    return this.scaledHeight(weightedHeight)
}

var Component.responsiveUIId: String
    get() = this.uiid
    set(value) {
        val isTablet = isTablet()
        val postFix = if (isTablet) "Tab" else ""
        val responseId = value + postFix;
        this.uiid = responseId
    }

fun Component.withResponsiveId(id:String) : Component{
    this.responsiveUIId = id
    return this
}

fun getResponsiveGridLayout(): Layout {
    val isTablet = isTablet();
    val totalGridItems = if (isTablet) 2 else 1
    val layout = if (totalGridItems > 1) GridLayout(totalGridItems, totalGridItems) else BoxLayout.y()
    return layout
}