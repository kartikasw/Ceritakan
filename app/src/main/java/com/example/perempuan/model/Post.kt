package com.example.perempuan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val uid: String = "",
    val user_uid : String? = "",
    val status: String? = "",
    val category: String? = "",
    val title: String? = "",
    val content: String? = "",
    var likeCount: Int? = 0,
    ): Parcelable {}
