package com.tlpcraft.cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform