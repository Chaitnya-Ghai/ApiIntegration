package com.example.apiintegration

// sealed is a keyword used to represent restricted class hierarchies
// response handle krne kai liye use krte esee
sealed class SealedClass<out T> {
    data class Success<out T>(val data: T?=null) : SealedClass<T>()
    data class Loading (val nothing: Nothing ?=null):SealedClass<Nothing>()
    data class Error(val mesg: String?=null) : SealedClass<Nothing>()
}