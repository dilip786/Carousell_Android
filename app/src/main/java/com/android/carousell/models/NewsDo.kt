package com.android.carousell.models

import com.google.gson.annotations.SerializedName

data class NewsDo(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("banner_url")
    val banner_url: String? = null,

    @SerializedName("time_created")
    val time_created: Long? = null,

    @SerializedName("rank")
    val rank: Int? = null
)
