package mykyta.titov.categorychallenge.core.providers

abstract class Provider<T> {
    abstract fun get(): T
}