package com.example

import kotlinx.serialization.UnstableDefault
import pl.treksoft.kvision.hmr.ApplicationBase
import pl.treksoft.kvision.hmr.module
import kotlin.browser.document

@UnstableDefault
fun main(args: Array<String>) {
    var application: ApplicationBase? = null

    val state: dynamic = module.hot?.let { hot ->
        hot.accept()

        hot.dispose { data ->
            data.appState = application?.dispose()
            application = null
        }

        hot.data
    }

    if (document.body != null) {
        application = start(state)
    } else {
        application = null
        document.addEventListener("DOMContentLoaded", { application = start(state) })
    }
}

@UnstableDefault
fun start(state: dynamic): ApplicationBase? {
    if (document.getElementById("kvapp") == null) return null
    @Suppress("UnsafeCastFromDynamic")
    App.start(state?.appState ?: emptyMap<String, Any>())
    return App
}
