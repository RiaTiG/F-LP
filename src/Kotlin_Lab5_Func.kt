class Kotlin_Lab5_Func {

    fun minNum(num: Int): Int{
        if (num == 0) return 0
        var min = 9
        var temp = num
        while (temp != 0){
            val digit = temp % 10
            if (digit < min){
                min = digit
            }
            temp /= 10
        }
        return min
    }

    fun kolNum3(num: Int): Int{
        if (num == 0) return 1
        var count = 0
        var temp = num
        while (temp != 0){
            val digit = temp % 10
            if (digit < 3){
                count++
            }
            temp /= 10
        }
        return count
    }

    fun delsNum(num: Int): Int{
        var count = 0
        for (i in 1.. num){
            if (num % i == 0) count ++
        }
        return count
    }

    //Найти минимальную цифру числа. Рекурсия вверх
    fun minNumRecUP(num: Int): Int =
        if (num < 10) num else if (num % 10 < minNumRecUP(num / 10))
             num % 10 else minNumRecUP(num / 10)

    //Найти количество цифр числа <3. Рекурсия вверх
    fun kolNum3RecUP(num: Int): Int =
        if (num == 0 ) 0 else if (num < 10 && num < 3) 1 else
            if (num % 10 < 3 ) 1 + kolNum3RecUP(num / 10)
            else kolNum3RecUP(num)

    //Найти количество делителей числа. Рекурсия вверх
    fun delsNumRecUP(num: Int, divisor: Int = 1): Int {
        if (divisor > num / 2) {
            return if (num % divisor == 0) 2 else 1
        }
        if (num % divisor == 0) {
            return 1 + delsNumRecUP(num, divisor + 1)
        } else {
            return delsNumRecUP(num, divisor + 1)
        }
    }

    //Найти минимальную цифру числа. Хвостовая рекурсия
    tailrec fun minNumRecTail(n: Int, minDigit: Int = 9): Int {
        if (n == 0) {
            return minDigit
        }
        val currentDigit = n % 10
        return minNumRecTail(n / 10, if (currentDigit < minDigit) currentDigit else minDigit)
    }

    //Найти количество цифр числа <3. Хвостовая рекурсия
    tailrec fun kolNum3RecTail(num: Int, count: Int = 0): Int {
        if (num == 0) {
            return count
        }
        val currentDigit = num % 10
        return kolNum3RecTail(num / 10, if (currentDigit < 3) count + 1 else count)
    }

    //Найти количество делителей числа. Хвостовая рекурсия
    tailrec fun delsNumRecTail(num: Int, divisor: Int = 1, count: Int = 0): Int {
        if (num < divisor) {
            return count
        }
        return if (num % divisor == 0) {
            delsNumRecTail(num, divisor + 1, count + 1)
        } else {
            delsNumRecTail(num, divisor + 1, count)
        }
    }

    fun main() {
        //val scanner = Scanner(`in`)
        //val x: Int = scanner.nextInt()
        println()
    }
}

fun main() = Kotlin_Lab5_Func().main()