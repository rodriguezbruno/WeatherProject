package com.example.weatherproject.utils

open class Event<out T>(private val content: T) {
    fun peekContent(): T = content
}