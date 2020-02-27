package com.codesroots.androidprojects.wokhouse.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize

data class ItemsModel (val data: List<ItemData>? = null
)  : Parcelable {

}

@Parcelize

data class ItemData (
    val id: Long? = null,
    val name: String? = null,
    val taxID: Long? = null,
    val price: String? = null,
    val subcategoryID: Long? = null,
    val created: String? = null,
    val modified: String? = null,
    val photo: String? = null,
    val description: String? = null,
    val tax: Tax? = null,
    val item_photo: List<ItemPhoto>? = null

)  : Parcelable {

}

@Parcelize

data class Tax (
    val id: Long? = null,
    val percentage: Long? = null,
    val name: String? = null,
    val created: String? = null,
    val modified: String? = null,
    val service: Long? = null
)  : Parcelable {

}
@Parcelize

data class ItemPhoto (
    val id: Long? = null,
    val photo: String?  = null
)  : Parcelable {

}