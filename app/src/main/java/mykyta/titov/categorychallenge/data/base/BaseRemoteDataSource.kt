package mykyta.titov.categorychallenge.data.base

import retrofit2.Retrofit

abstract class BaseRemoteDataSource(
        protected val retrofit: Retrofit
) {

    internal fun <S> createService(clazz: Class<S>) = retrofit.create(clazz)
}