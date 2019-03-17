package mykyta.titov.categorychallenge.domain

import android.os.Parcel
import android.os.Parcelable
import mykyta.titov.categorychallenge.domain.Category.Weight.NORMAL
import mykyta.titov.categorychallenge.utils.extensions.orDefault
import mykyta.titov.categorychallenge.utils.extensions.readEnum
import mykyta.titov.categorychallenge.utils.extensions.writeEnum

class Category(
        val id: String,
        val imageUrl: String,
        var popularity: Int = 0,
        var weight: Weight = NORMAL
) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString().orDefault(),
            source.readString().orDefault(),
            source.readInt(),
            source.readEnum<Weight>() ?: NORMAL
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(imageUrl)
        writeInt(popularity)
        writeEnum(weight)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Category> = object : Parcelable.Creator<Category> {
            override fun createFromParcel(source: Parcel): Category = Category(source)
            override fun newArray(size: Int): Array<Category?> = arrayOfNulls(size)
        }
    }

    enum class Weight {
        BIG,
        MEDIUM,
        NORMAL
    }
}