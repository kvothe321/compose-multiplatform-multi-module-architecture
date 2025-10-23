package com.tlpcraft.cmp

import com.tlpcraft.cmp.application.ApplicationClass
import com.tlpcraft.cmp.domain.JustMyClass

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        val x: JustMyClass = JustMyClass()
        val y: ApplicationClass = ApplicationClass()
        return "Hello, ${platform.name}!"
    }
}