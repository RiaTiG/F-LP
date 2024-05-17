import java.lang.System.`in`
import java.util.*

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

    fun minNumRec(num: Int): Int =
        if (num < 10) num else if (num % 10 < minNumRec(num / 10))
             num % 10 else minNumRec(num / 10)


    fun kolNum3Rec(num: Int): Int =
        if (num == 0 ) 0 else if (num < 10 && num < 3) 1 else
            if (num % 10 < 3 ) 1 + kolNum3Rec(num / 10)
            else kolNum3Rec(num / 10)

    fun delsNumRec(num: Int): Int =
        if ()

    fun main() {
        val scanner = Scanner(`in`)
        val x: Int = scanner.nextInt()
        println(kolNum3Rec(x))
    }
}

fun main() = Kotlin_Lab5_Func().main()