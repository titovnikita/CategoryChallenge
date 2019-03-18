package mykyta.titov.categorychallenge.mappers

import mykyta.titov.categorychallenge.data.dtos.CategoryDto
import mykyta.titov.categorychallenge.data.dtos.CoverPhotoDto
import mykyta.titov.categorychallenge.data.dtos.UrlsDto
import mykyta.titov.categorychallenge.data.mappers.CategoriesMapper
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.domain.Category.Weight.NORMAL
import mykyta.titov.tracking.data.entities.TrackingEntity
import org.junit.Test

class CategoriesMapperTest {

    private val categoriesMapper = CategoriesMapper()

    @Test
    fun `Map category dto to category with nulls`() {
        val categoryDto = CategoryDto(null, null)

        val categoryMapped: Category = categoriesMapper.transform(categoryDto)
        with(categoryMapped) {
            assert(id.isEmpty())
            assert(imageUrl.isEmpty())
            assert(popularity == 0)
            assert(weight == NORMAL)
        }
    }

    @Test
    fun `Map category dto to category with data`() {
        val categoryId = "id"
        val regularImageUrl = "regular"

        val urlsDto = UrlsDto(regularImageUrl)
        val coverPhoto = CoverPhotoDto(urlsDto)
        val categoryDto = CategoryDto(categoryId, coverPhoto)

        val categoryMapped: Category = categoriesMapper.transform(categoryDto)
        with(categoryMapped) {
            assert(id.isNotEmpty())
            assert(id == categoryId)
            assert(imageUrl.isNotEmpty())
            assert(imageUrl == regularImageUrl)
            assert(popularity == 0)
            assert(weight == NORMAL)
        }
    }

    @Test
    fun `Map category dto to category without id with data`() {
        val regularImageUrl = "regular"

        val urlsDto = UrlsDto(regularImageUrl)
        val coverPhoto = CoverPhotoDto(urlsDto)
        val categoryDto = CategoryDto(null, coverPhoto)

        val categoryMapped: Category = categoriesMapper.transform(categoryDto)
        with(categoryMapped) {
            assert(id.isEmpty())
            assert(imageUrl.isNotEmpty())
            assert(imageUrl == regularImageUrl)
            assert(popularity == 0)
            assert(weight == NORMAL)
        }
    }

    @Test
    fun `Map category dto to category with id without data`() {
        val categoryId = "id"
        val categoryDto = CategoryDto(categoryId, null)

        val categoryMapped: Category = categoriesMapper.transform(categoryDto)
        with(categoryMapped) {
            assert(id.isNotEmpty())
            assert(id == categoryId)
            assert(imageUrl.isEmpty())
            assert(popularity == 0)
            assert(weight == NORMAL)
        }
    }

    @Test
    fun `Map tracking entity to category with id and data`() {
        val trackingId = 321
        val categoryId = "category_id"
        val categoryImageUrl = "category_image_url"
        val categoryPopularity = 123
        val trackingEntity = TrackingEntity(trackingId, categoryId, categoryImageUrl, categoryPopularity)

        val categoryMapped: Category = categoriesMapper.transform(trackingEntity)
        with(categoryMapped) {
            assert(id.isNotEmpty())
            assert(id == categoryId)
            assert(imageUrl.isNotEmpty())
            assert(imageUrl == categoryImageUrl)
            assert(popularity == categoryPopularity)
            assert(weight == NORMAL)
        }
    }

    @Test
    fun `Map tracking entity to category with null id and valid data`() {
        val categoryId = "category_id"
        val categoryImageUrl = "category_image_url"
        val categoryPopularity = 123
        val trackingEntity = TrackingEntity(null, categoryId, categoryImageUrl, categoryPopularity)

        val categoryMapped: Category = categoriesMapper.transform(trackingEntity)
        with(categoryMapped) {
            assert(id.isNotEmpty())
            assert(id == categoryId)
            assert(imageUrl.isNotEmpty())
            assert(imageUrl == categoryImageUrl)
            assert(popularity == categoryPopularity)
            assert(weight == NORMAL)
        }
    }

    @Test
    fun `Map category to tracking entity`() {
        val stubId = "category_id"
        val imageUrl = "category_image_url"
        val categoryPopularity = 123
        val weight = Category.Weight.BIG
        val category = Category(stubId, imageUrl, categoryPopularity, weight)

        val entityMapped: TrackingEntity = categoriesMapper.transform(category)
        with(entityMapped) {
            assert(id == null)
            assert(categoryId.isNotEmpty())
            assert(categoryId == stubId)
            assert(categoryImageUrl.isNotEmpty())
            assert(categoryImageUrl == imageUrl)
            assert(popularity == categoryPopularity)
        }
    }
}