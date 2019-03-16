package mykyta.titov.categorychallenge.core

import mykyta.titov.categorychallenge.core.ViewModelFactory

interface ApplicationBridge {
    fun getViewModelFactory(): ViewModelFactory
}