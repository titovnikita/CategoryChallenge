package mykyta.titov.categorychallenge.domain

import android.os.Parcel
import android.os.Parcelable
import mykyta.titov.categorychallenge.utils.extensions.orDefault

class Item(
        val id: String,
        val imageUrl: String
) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString().orDefault(),
            source.readString().orDefault()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(imageUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Item> = object : Parcelable.Creator<Item> {
            override fun createFromParcel(source: Parcel): Item = Item(source)
            override fun newArray(size: Int): Array<Item?> = arrayOfNulls(size)
        }
    }
}