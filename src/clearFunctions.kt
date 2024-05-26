class сlearFunctions(){
    tailrec fun arrayOP(m: List<Int>, f: (Int, Int) -> Int, i: Int = m.size, a: Int = 0): Int =
        if (i <= 0) a else arrayOP(m, f, i - 1, f(a, m[i - 1]))

    tailrec fun findInList(list: List<Int>, predicate: (Int) -> Boolean, index: Int = list.size): Boolean {
        if (index <= 0) {
            return false
        } else if (predicate(list[index - 1])) {
            return true
        } else {
            return findInList(list, predicate, index - 1)
        }
    }

    // Число элементов списка, которые могут быть квадратами другого числа.
    fun squareList(list: List<Int>): Int {
        return arrayOP(list, { count, el ->
            if (findInList(list, { it == el * el })) count + 1 else count
        })
    }

    fun sumEl(n: Int): Int = if (n < 10) n else (n % 10) + sumEl(n / 10)
    fun countDels(n: Int): Int = (1..n).count { n % it == 0 }
    fun thirdList(list1: List<Int>, list2: List<Int>, list3: List<Int>): List<Triple<Int, Int, Int>> {
        return list1.sortedDescending()
            .mapIndexed { index, value ->
                Triple(
                    value,
                    list2.sortedBy { sumEl(it) }[index],
                    list3.sortedByDescending { countDels(it) }[index]
                )
            }
    }

    //количество элементов, расположенных после последнего максимального.
    fun elementsAfterMax(list: List<Int>): Int {
        val lastIndex = list.indexOfLast { it == list.maxOrNull() }
        return list.drop(lastIndex + 1).count()
    }

    //переставить в обратном порядке элементы массива, расположенные между его минимальным и максимальным элементами.
    fun reverseMinMax(list: List<Int>): List<Int> {
        val minIndex = list.indexOf(list.minOrNull())
        val maxIndex = list.indexOf(list.maxOrNull())
        val (firstIndex, lastIndex) = if (minIndex < maxIndex) minIndex to maxIndex else maxIndex to minIndex
        val beforeMinMax = list.subList(0, firstIndex + 1)
        val minMax = list.subList(firstIndex + 1, lastIndex).reversed()
        val afterMinMax = list.subList(lastIndex, list.size)
        return beforeMinMax + minMax + afterMinMax
    }

    //разместить элементы, расположенные до минимального, в конце массива.
    fun moveMin(list: List<Int>): List<Int> {
        val minIndex = list.indexOf(list.minOrNull())
        val beforeMin = list.subList(0, minIndex)
        val afterMin = list.subList(minIndex, list.size)
        return afterMin + beforeMin
    }

    //циклический сдвиг элементов массива вправо на одну позицию.
    fun goRight(list: List<Int>): List<Int> {
        val lastElement = list.last()
        val allNoLast = list.dropLast(1)
        return listOf(lastElement) + allNoLast
    }

    //циклический сдвиг элементов массива влево на одну позицию.
    fun goLeft(list: List<Int>): List<Int> {
        val firstElement = list.first()
        val allNoFirst = list.drop(1)
        return allNoFirst + listOf(firstElement)
    }

    //максимальный нечетный элемент.
    fun maxOdd(list: List<Int>): Int {
        return list.filter { it % 2 != 0 }.maxOrNull()!!
    }

}
fun main(){
    val c = сlearFunctions()
    println(c.maxOdd(listOf(3,15,75,15,17)))
}