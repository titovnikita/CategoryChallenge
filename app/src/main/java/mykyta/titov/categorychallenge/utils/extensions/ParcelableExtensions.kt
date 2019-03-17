package mykyta.titov.categorychallenge.utils.extensions

import android.os.Parcel

inline fun <reified T : Enum<T>> Parcel.readEnum() = readInt().let { if (it >= 0) enumValues<T>()[it] else null }

fun <T : Enum<T>> Parcel.writeEnum(value: T?) = writeInt(value?.ordinal ?: -1)