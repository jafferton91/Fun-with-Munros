package io.munros.test

import io.munros.library.data.enums.MunroCategory
import io.munros.library.data.enums.SortDirection
import io.munros.library.makeNice
import io.munros.library.sortByCategory
import io.munros.library.sortByName
import io.munros.library.util.CsvData
import io.munros.test.helper.createCsvDataForTest
import org.junit.Test
import kotlin.test.assertEquals

class ChainedMunroAwesomeTests : BaseTest() {

    @Test fun sortByCategoryThenByName() {
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(elfNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, 2000.0, MunroCategory.Top))

        val listOfMunros = testSet.sortByCategory(MunroCategory.Munro)?.sortByName(SortDirection.Asc)?.makeNice()
        val listOfTops = testSet.sortByCategory(MunroCategory.Top)?.sortByName(SortDirection.Asc)?.makeNice()

        // category sort split
        assertEquals(3, listOfMunros?.size)
        assertEquals(3, listOfTops?.size)

        // munros
        assertEquals(amyNevis, listOfMunros!![0].name)
        assertEquals(chrisNevis, listOfMunros[1].name)
        assertEquals(elfNevis, listOfMunros[2].name)

        // tops
        assertEquals(benNevis, listOfTops!![0].name)
        assertEquals(daveNevis, listOfTops[1].name)
        assertEquals(funNevis, listOfTops[2].name)

    }

}