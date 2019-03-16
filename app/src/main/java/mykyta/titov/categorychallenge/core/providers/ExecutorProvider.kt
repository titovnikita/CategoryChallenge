package mykyta.titov.categorychallenge.core.providers

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ExecutorProvider : Provider<Executor>() {

    private val executor: Executor by lazy { Executors.newSingleThreadExecutor() }

    override fun get(): Executor = executor
}