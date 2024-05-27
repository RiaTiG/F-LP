class Functions() {
    //элементы, которые меньше среднего арифметического элементов массива.
    fun countAverage(list : List<Int>) : List<Int> {
        val average = list.average()
        val result = list.filter { it < average }
        return result
    }

    //список с номерами элемента, который повторяется наибольшее число раз.
    fun indexOfMaxCount(list : List<Int>) : List<Int> {
        val groupedElements = list.withIndex().groupBy { it.value }
        val elementWithMaxCount = groupedElements.maxByOrNull { it.value.size }?.key
        val result = elementWithMaxCount?.let { key -> groupedElements[key]?.map { it.index } ?: emptyList() } ?: emptyList()
        return result
    }

    // список из элементов, встречающихся в исходном более трех раз.
    fun elemsMoreThenTree (list : List<Int>) : List<Int> {
        val elementCounts = list.groupingBy { it }.eachCount()
        val frequentElements = elementCounts.filter { it.value > 3 }.keys
        val result = list.filter { it in frequentElements }.distinct()
        return result
    }

    //количество элементов, которые могут быть получены как сумма двух любых других элементов списка.
    fun sumsOfElements(list : List<Int>) : Int {
        val sums = list.flatMap { x -> list.map { y -> x + y } }
        val firstSums = sums.filter { it > 0 }.distinct()
        val count = list.count { it in firstSums }
        return count
    }
}
fun main() {
    val c = Functions()
    println(c.sumsOfElements(listOf(3,12,15,75,15,30,17)))
}