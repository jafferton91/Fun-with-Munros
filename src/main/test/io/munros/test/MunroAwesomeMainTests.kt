package io.munros.test

import io.munros.library.data.enums.MunroCategory
import io.munros.library.data.enums.SortDirection
import io.munros.library.makeNice
import io.munros.library.sortByCategory
import io.munros.library.sortByName
import io.munros.library.util.CsvData
import io.munros.test.helper.createCsvDataForTest
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

internal class MunroAwesomeMainTests {

    @BeforeEach fun setUp() {
    }

    @AfterEach fun tearDown() {
    }

    private val amyNevis = "Amy Nevis"
    private val benNevis = "Ben Nevis"
    private val chrisNevis = "Chris Nevis"
    private val daveNevis = "Dave Nevis"
    private val elfNevis = "Elf Nevis"
    private val funNevis = "Fun Nevis"

    @Test fun sortByCategory() {
        val testSet = ArrayList<CsvData>()
        testSet.add(createCsvDataForTest(amyNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(benNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(chrisNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(elfNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(funNevis, 2000.0, MunroCategory.Top))

        val listOfMunros = testSet.sortByCategory(MunroCategory.Munro)?.makeNice()
        val listOfTops = testSet.sortByCategory(MunroCategory.Top)?.makeNice()

        assertEquals(3, listOfMunros?.size)
        assertEquals(3, listOfTops?.size)
    }

    @Test fun sortByName() {
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, 2000.0, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(elfNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, 2000.0, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, 2000.0, MunroCategory.Top))

        val listAsc = testSet.sortByName(SortDirection.Asc)?.makeNice()
        val listDesc = testSet.sortByName(SortDirection.Desc)?.makeNice()

        // asc order
        assertEquals(amyNevis, listAsc!![0].name)
        assertEquals(benNevis, listAsc[1].name)
        assertEquals(chrisNevis, listAsc[2].name)
        assertEquals(daveNevis, listAsc[3].name)
        assertEquals(elfNevis, listAsc[4].name)
        assertEquals(funNevis, listAsc[5].name)

        // desc order
        assertEquals(funNevis, listDesc!![0].name)
        assertEquals(elfNevis, listDesc[1].name)
        assertEquals(daveNevis, listDesc[2].name)
        assertEquals(chrisNevis, listDesc[3].name)
        assertEquals(benNevis, listDesc[4].name)
        assertEquals(amyNevis, listDesc[5].name)

    }

    @Test  fun sortByHeight() {
    }

    @Test  fun filterHeights() {
    }

    @Test  fun makeNice() {
    }

}