package com.mb.model

data class Comics(
    val available : Int,
    val returned : Int,
    val collectionURI : String,
    val items : MutableList<Item>
)