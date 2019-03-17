package mykyta.titov.categorychallenge.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, addToBackStack: Boolean = true) {
    supportFragmentManager?.inTransaction {
        add(frameId, fragment).also {
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, addToBackStack: Boolean = true) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment).also {
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
    }
}