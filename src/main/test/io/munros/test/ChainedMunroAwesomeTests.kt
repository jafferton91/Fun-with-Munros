package io.munros.test

import io.munros.library.data.enums.MunroCategory
import io.munros.library.data.enums.SortDirection
import io.munros.library.endChain
import io.munros.library.sortByCategory
import io.munros.library.sortByHeight
import io.munros.library.sortByName
import io.munros.library.util.CsvData
import io.munros.test.helper.createCsvDataForTest
import org.junit.Test
import kotlin.test.assertEquals

class ChainedMunroAwesomeTests : BaseTest() {

    @Test fun sortByCategoryThenByName() {
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, medium, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, smaller, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, biggest, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(ellieNevis, small, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, big, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, smallest, MunroCategory.Top))

        val listOfMunros = testSet.sortByCategory(MunroCategory.Munro)?.sortByName(SortDirection.Asc)?.endChain()
        val listOfTops = testSet.sortByCategory(MunroCategory.Top)?.sortByName(SortDirection.Asc)?.endChain()

        // category sort split
        assertEquals(3, listOfMunros?.size)
        assertEquals(3, listOfTops?.size)

        // munros
        assertEquals(amyNevis, listOfMunros!![0].name)
        assertEquals(chrisNevis, listOfMunros[1].name)
        assertEquals(ellieNevis, listOfMunros[2].name)

        // tops
        assertEquals(benNevis, listOfTops!![0].name)
        assertEquals(daveNevis, listOfTops[1].name)
        assertEquals(funNevis, listOfTops[2].name)

    }

    @Test fun sortByNameThenByHeight() {
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, medium, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, smaller, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, biggest, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(ellieNevis, small, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, big, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, smallest, MunroCategory.Top))

        val listOfMunros = testSet.sortByName(SortDirection.Asc)?.sortByHeight(SortDirection.Asc)?.endChain()

        // category sort split
        assertEquals(6, listOfMunros?.size)

        // should be in height order
        assertEquals(smallest, listOfMunros!![0].height)
        assertEquals(smaller, listOfMunros[1].height)
        assertEquals(small, listOfMunros[2].height)
        assertEquals(medium, listOfMunros[3].height)
        assertEquals(big, listOfMunros[4].height)
        assertEquals(biggest, listOfMunros[5].height)

    }

}