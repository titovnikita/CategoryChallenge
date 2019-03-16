package mykyta.titov.categorychallenge.utils.extensions

import androidx.fragment.app.Fragment

fun Fragment.addFragment(fragment: Fragment, frameId: Int) {
    fragmentManager?.inTransaction {
        add(frameId, fragment).also { addToBackStack(null) }
    }
}


fun Fragment.replaceFragment(fragment: Fragment, frameId: Int) {
    fragmentManager?.inTransaction { replace(frameId, fragment) }
}