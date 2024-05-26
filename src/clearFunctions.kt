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

    // Число элементов списка, которые могут быть квадратами другого числа
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
}
fun main(){
    val c = сlearFunctions()
    println(c.thirdList(listOf(3,15,75), listOf(3,25,35),listOf(9,11,21)))
}