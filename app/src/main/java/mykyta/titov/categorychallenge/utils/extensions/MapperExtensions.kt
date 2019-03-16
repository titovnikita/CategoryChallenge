package mykyta.titov.categorychallenge.utils.extensions

fun String?.orDefault(default: String = DEFAULT_STRING) = this ?: default
fun <T> List<T>?.orDefault(default: List<T> = listOf()) = this ?: default

const val DEFAULT_STRING = ""