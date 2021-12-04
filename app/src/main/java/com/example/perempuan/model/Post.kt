package com.example.perempuan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.security.Timestamp

@Parcelize
data class Post(
    val uid: String = "",
    val user_uid: String? = "",
    var status: String? = "",
    val category: String? = "",
    var title: String? = "",
    var content: String? = "",
    var likeCount: Int? = 0
    ): Parcelable {}
