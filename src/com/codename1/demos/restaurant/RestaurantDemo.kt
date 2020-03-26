package com.codename1.demos.restaurant

import com.codename1.io.Log
import com.codename1.io.NetworkEvent
import com.codename1.ui.CN.*
import com.codename1.ui.Dialog
import com.codename1.ui.Form
import com.codename1.ui.Toolbar
import com.codename1.ui.plaf.UIManager
import com.codename1.ui.util.Resources

open class RestaurantDemo {
    private var current: Form? = null
    private var theme: Resources? = null
    fun init(context: Any?) {
        updateNetworkThreadCount(2)
        theme = UIManager.initFirstTheme("/theme")

        Toolbar.setGlobalToolbar(true)

        Log.bindCrashProtection(true)
        addNetworkErrorListener { err: NetworkEvent ->
            err.consume()
            if (err.error != null) {
                Log.e(err.error)
            }
            Log.sendLogAsync()
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.connectionRequest.url, "OK", null)
        }
    }

    fun start() {
        if (current != null) {
            current?.show()
            return
        }
        showSplashScreen(theme!!)
        //showCustomForm()
    }

    fun stop() {
        current = getCurrentForm()
        if (current is Dialog) {
            (current as Dialog).dispose()
            current = getCurrentForm()
        }
    }

    fun destroy() {}
}