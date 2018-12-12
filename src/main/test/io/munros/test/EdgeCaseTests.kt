package io.munros.test

import io.munros.library.data.enums.MunroCategory
import io.munros.library.filterHeights
import io.munros.library.makeNice
import io.munros.library.util.CsvData
import io.munros.library.util.MunroException
import io.munros.test.helper.createCsvDataForTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class EdgeCaseTests : BaseTest() {

    @Test fun filterHeightsAndLimitResultToTwo() {

        val numberToTake = 2
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, medium, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, smaller, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, biggest, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(elfNevis, small, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, big, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, smallest, MunroCategory.Top))

        val listFilteredHeights = testSet.filterHeights(small, big)?.makeNice()?.take(numberToTake)

        assertEquals(numberToTake, listFilteredHeights?.size)

    }

    @Test fun filterHeightsAndMinHeightExceedsMaxHeight() {
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, medium, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, smaller, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, biggest, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(elfNevis, small, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, big, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, smallest, MunroCategory.Top))

        // Min height is larger than max height
        assertFailsWith(MunroException::class) { testSet.filterHeights(big, small)?.makeNice() }

    }

}