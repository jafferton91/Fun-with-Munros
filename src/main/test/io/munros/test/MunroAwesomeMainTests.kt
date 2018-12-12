package io.munros.test

import io.munros.library.*
import io.munros.library.data.enums.MunroCategory
import io.munros.library.data.enums.SortDirection
import io.munros.library.util.CsvData
import io.munros.test.helper.createCsvDataForTest
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertEquals

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

    private val smallest = 1000.0
    private val smaller = 3000.0
    private val small = 5000.0
    private val medium = 6000.0
    private val big = 8000.0
    private val biggest = 10000.0


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
        val testSet = ArrayList<CsvData>()

        testSet.add(createCsvDataForTest(benNevis, medium, MunroCategory.Top))
        testSet.add(createCsvDataForTest(funNevis, smaller, MunroCategory.Top))
        testSet.add(createCsvDataForTest(amyNevis, biggest, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(elfNevis, small, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, big, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, smallest, MunroCategory.Top))

        val listAsc = testSet.sortByHeight(SortDirection.Asc)?.makeNice()
        val listDesc = testSet.sortByHeight(SortDirection.Desc)?.makeNice()

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
        testSet.add(createCsvDataForTest(elfNevis, small, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(chrisNevis, big, MunroCategory.Munro))
        testSet.add(createCsvDataForTest(daveNevis, smallest, MunroCategory.Top))

        val listFilteredHeights = testSet.filterHeights(small, big)?.makeNice()

        assertEquals(3, listFilteredHeights?.size)

    }

}