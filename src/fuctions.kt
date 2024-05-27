import kotlin.math.abs

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

    //------------------------------------------------------------

    //существует пара (x,y): X*Y=N, НОД(X,Y)=d, a=X/d, b=Y/d .
    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    fun pairGcd(N:Int,d:Int): List<Pair<Int, Int>> {
        fun divisors(n: Int): List<Int> = (1..n).filter { n % it == 0 }
        val pairs = divisors(N).flatMap { x ->
            divisors(N / x).map { y ->
                Pair(x, y)
            }
        }
        val result = pairs.filter { (x, y) -> gcd(x, y) == d }.map { (x, y) -> Pair(x / d, y / d) }.distinct()
        return result
    }

    //построить список из элементов, для которых в данном списке встречаются простые делители.
    private fun checkIfDivider(element: Int, divider: Int) = element % divider == 0

    // Функция для подсчета количества делителей числа
    private fun getDividerCounter(element: Int, current: Int = 1, counter: Int = 0): Int {
        return if (current > element) {
            counter
        } else {
            getDividerCounter(element, current + 1, if (checkIfDivider(element, current)) counter + 1 else counter)
        }
    }

    // Функция проверки является ли число простым
    private fun checkIfSimple(element: Int) = getDividerCounter(element) == 2

    // Рекурсивная функция проверки является ли число простым делителем числа N
    private fun checkIfSimpleDivider(N: Int, currentDivider: Int): Boolean {
        return checkIfSimple(currentDivider) && checkIfDivider(N, currentDivider)
    }

    // Функция получения списка всех простых делителей числа
    private fun listOfSimpleDividers(N: Int): List<Int> {
        return (2..N).filter { checkIfSimpleDivider(N, it) }
    }

    // Функция проверки наличия всех простых делителей числа в списке
    private fun checkListForSimple(list: List<Int>, element: Int): Boolean {
        val simpleDividers = listOfSimpleDividers(element)
        return simpleDividers.all { it in list }
    }

    // Функция создания нового списка из элементов, для которых все их простые делители есть в оригинальном списке
    fun createResultList(list: List<Int>): List<Int> {
        return list.filter { element ->
            checkListForSimple(list, element)
        }
    }

    //построить список, где каждый элемент – это среднее арифметическое тех цифр соответствующего числа из исходного списка
    fun processList(list: List<Int>): List<Double> {
        val digitFrequencies = list.flatMap { number ->
            number.toString().map { char -> char.digitToInt() }
        }.groupingBy { it }.eachCount()
        val totalDigits = digitFrequencies.values.sum()
        val threshold = totalDigits / 20.0
        val frequentDigits = digitFrequencies.filter { it.value > threshold }.keys
        return list.map { number ->
            val digits = number.toString().map { char -> char.digitToInt() }
            val frequentDigitsInNumber = digits.filter { it in frequentDigits }
            if (frequentDigitsInNumber.isNotEmpty()) {
                frequentDigitsInNumber.average()
            } else {
                0.0
            }
        }
    }
}
fun main() {
    val c = Functions()
    println(c.processList(listOf(74, 12, 55, 87, 90)))
}