package io.munros.test

import io.munros.library.*
import io.munros.library.data.enums.MunroCategory
import io.munros.library.data.enums.SortDirection
import io.munros.library.data.model.CsvData
import io.munros.test.helper.createCsvDataForTest
import org.junit.Test
import kotlin.test.assertEquals

internal class MunroAwesomeCoreTests : BaseTest() {

    @Test fun sortByCategory() {
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(amyNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(benNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(chrisNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(ellieNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(funNevis, 2000.0, MunroCategory.Top))

        val listOfMunros = testSet.sortByCategory(MunroCategory.Munro)?.endChain()
        val listOfTops = testSet.sortByCategory(MunroCategory.Top)?.endChain()

        assertEquals(3, listOfMunros?.size)
        assertEquals(3, listOfTops?.size)

    }

    @Test fun sortByName() {
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(ellieNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, 2000.0, MunroCategory.Top))

        val listAsc = testSet.sortByName(SortDirection.Asc)?.endChain()
        val listDesc = testSet.sortByName(SortDirection.Desc)?.endChain()

        // asc order
        assertEquals(amyNevis, listAsc!![0].name)
        assertEquals(benNevis, listAsc[1].name)
        assertEquals(chrisNevis, listAsc[2].name)
        assertEquals(daveNevis, listAsc[3].name)
        assertEquals(ellieNevis, listAsc[4].name)
        assertEquals(funNevis, listAsc[5].name)

        // desc order
        assertEquals(funNevis, listDesc!![0].name)
        assertEquals(ellieNevis, listDesc[1].name)
        assertEquals(daveNevis, listDesc[2].name)
        assertEquals(chrisNevis, listDesc[3].name)
        assertEquals(benNevis, listDesc[4].name)
        assertEquals(amyNevis, listDesc[5].name)

    }

    @Test  fun sortByHeight() {
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, medium, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, smaller, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, biggest, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(ellieNevis, small, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, big, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, smallest, MunroCategory.Top))

        val listAsc = testSet.sortByHeight(SortDirection.Asc)?.endChain()
        val listDesc = testSet.sortByHeight(SortDirection.Desc)?.endChain()

        // asc order
        assertEquals(smallest, listAsc!![0].height)
        assertEquals(smaller, listAsc[1].height)
        assertEquals(small, listAsc[2].height)
        assertEquals(medium, listAsc[3].height)
        assertEquals(big, listAsc[4].height)
        assertEquals(biggest, listAsc[5].height)

        // desc order
        assertEquals(biggest, listDesc!![0].height)
        assertEquals(big, listDesc[1].height)
        assertEquals(medium, listDesc[2].height)
        assertEquals(small, listDesc[3].height)
        assertEquals(smaller, listDesc[4].height)
        assertEquals(smallest, listDesc[5].height)

    }

    @Test  fun filterHeights() {
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, medium, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, smaller, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, biggest, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(ellieNevis, small, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, big, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, smallest, MunroCategory.Top))

        val listFilteredHeights = testSet.filterHeights(small, big)?.endChain()

        assertEquals(3, listFilteredHeights?.size)

    }

}