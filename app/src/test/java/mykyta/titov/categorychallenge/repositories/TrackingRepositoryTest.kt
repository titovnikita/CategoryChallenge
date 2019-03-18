package mykyta.titov.categorychallenge.repositories

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import mykyta.titov.categorychallenge.data.repositories.tracking.TrackingRepository
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito


class TrackingRepositoryTest {

    private val mockContentResolver: ContentResolver = mock()

    private val localDataSource = TrackingRepository.Local(mockContentResolver)

    private val trackingRepository = TrackingRepository(localDataSource)

    @Test
    fun `Get top clicked categories from repository no items`() {
        givenResult(null)

        val cursor = trackingRepository.queryByPopularity(ITEMS_COUNT)
        assertNull(cursor)
    }

    @Test
    fun `Get top clicked categories from repository with items`() {
        val mockCursor: Cursor = mock()
        whenever(mockCursor.count)
                .thenReturn(RESULT_COUNT)
        givenResult(mockCursor)

        val cursor = trackingRepository.queryByPopularity(ITEMS_COUNT)
        assertNotNull(cursor)
        assert(cursor?.count == RESULT_COUNT)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Increment click for category fail`() {
        val exception = IllegalArgumentException(EXCEPTION_MESSAGE)
        givenError(exception)

        trackingRepository.queryByPopularity(ITEMS_COUNT)
    }

    private fun givenResult(cursor: Cursor?) {
        whenever(mockContentResolver.query(Mockito.any<Uri>(), Mockito.any<Array<String>>(), Mockito.any<String>(), Mockito.any<Array<String>>(), anyString()))
                .thenReturn(cursor)
    }

    private fun givenError(exception: Exception) {
        whenever(mockContentResolver.query(Mockito.any<Uri>(), Mockito.any<Array<String>>(), Mockito.any<String>(), Mockito.any<Array<String>>(), anyString()))
                .thenThrow(exception)
    }

}

private const val ITEMS_COUNT = 5
private const val EXCEPTION_MESSAGE = "exception_message"
private const val RESULT_COUNT = 5