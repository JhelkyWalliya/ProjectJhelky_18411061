package com.example.projectjhelky_18411061_uts

import com.google.firebase.database.Exclude

data class Image(
    val imageSrc : String? = null,
    val imageTitle : String? = null,
    val imageDesc : String? = null,
    @get:Exclude
    @set:Exclude
    var key:String? = null
)