package mykyta.titov.categorychallenge.utils

import java.util.concurrent.Executor

class DirectExecutor : Executor {
    override fun execute(r: Runnable) {
        r.run()
    }
}