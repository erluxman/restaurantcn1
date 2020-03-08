package com.codename1.demos.restaurant

import com.codename1.io.Log
import com.codename1.io.NetworkEvent
import com.codename1.ui.*
import com.codename1.ui.layouts.BoxLayout
import com.codename1.ui.plaf.UIManager
import com.codename1.ui.util.Resources

open class RestaurantDemo {
    private var current: Form? = null
    private var theme: Resources? = null
    fun init(context: Any?) {
        CN.updateNetworkThreadCount(2)
        theme = UIManager.initFirstTheme("/theme")

        Toolbar.setGlobalToolbar(true)

        Log.bindCrashProtection(true)
        CN.addNetworkErrorListener { err: NetworkEvent ->

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
        val hi = Form("Hi World", BoxLayout.y())
        hi.add(Label("Hi World","SplashLabel"))
        hi.add(Label("Hi World","SplashLabelMini"))
        hi.show()
    }

    fun stop() {
        current = CN.getCurrentForm()
        if (current is Dialog) {
            (current as Dialog).dispose()
            current = CN.getCurrentForm()
        }
    }

    fun destroy() {}
}