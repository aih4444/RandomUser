package com.test.randomuser_mvvm_test.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
        var id: String?,
        var title: String,
        var first: String,
        var last: String,
        var email: String,
        var picture: String,
        var location: String
):Parcelable{}