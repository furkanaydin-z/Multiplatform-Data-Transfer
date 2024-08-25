package org.fako.millitakim

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform