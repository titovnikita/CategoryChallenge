package mykyta.titov.categorychallenge.utils.extensions

import androidx.fragment.app.Fragment

fun Fragment.addFragment(fragment: Fragment, frameId: Int, addToBackStack: Boolean = true) {
    fragmentManager?.inTransaction {
        add(frameId, fragment).also {
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
    }
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int, addToBackStack: Boolean = true) {
    fragmentManager?.inTransaction {
        replace(frameId, fragment).also {
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
    }
}