package com.mb.model

data class Results(
    val id : Int,
    val name : String,
    val description : String,
    val modified : String,
    val resourceURI : String,
    val thumbnail : Thumbnail,
    val urls : List<Urls>
)