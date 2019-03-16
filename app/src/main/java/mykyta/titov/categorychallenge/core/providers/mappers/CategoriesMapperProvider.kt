package mykyta.titov.categorychallenge.core.providers.mappers

import mykyta.titov.categorychallenge.core.providers.Provider
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper

class CategoriesMapperProvider : Provider<CategoriesMapper>() {

    private val categoriesMapper: CategoriesMapper by lazy { CategoriesMapper() }

    override fun get(): CategoriesMapper = categoriesMapper
}