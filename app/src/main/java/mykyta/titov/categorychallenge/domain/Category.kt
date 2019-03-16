package mykyta.titov.categorychallenge.domain

import android.os.Parcel
import android.os.Parcelable
import mykyta.titov.categorychallenge.utils.extensions.orDefault

class Category(
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
        val CREATOR: Parcelable.Creator<Category> = object : Parcelable.Creator<Category> {
            override fun createFromParcel(source: Parcel): Category = Category(source)
            override fun newArray(size: Int): Array<Category?> = arrayOfNulls(size)
        }
    }
}