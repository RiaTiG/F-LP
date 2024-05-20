import java.lang.System.`in`
import java.util.*
import Kotlin_Lab5_Func.*
import java.io.File
import kotlin.reflect.KFunction
import kotlin.reflect.*

var arr = ArrayList<ArrayList<String>>()

// Получение строк из файла.
fun FileInDirectory(filePath: String) {
    val lines = File(filePath).readLines()
    for (line in lines) {
        val newList = ArrayList<String>()
        val act = line.split(" ").toTypedArray()
        for (q in act) {
            newList.add(q)
        }
        arr.add(newList)
    }
}
// Получение функции по имени
fun getFunc(name: String): KFunction<Int>? {
    val Recursion = Kotlin_Lab5_Func()
    val funcs = mapOf(
        "minNum" to Recursion::minNum,
        "kolNum3" to Recursion::kolNum3,
        "delsNum" to Recursion::delsNum,
        "minNumRecUP" to Recursion::minNumRecUP,
        "kolNum3RecUP" to Recursion::kolNum3RecUP,
        "delsNumRecUP" to Recursion::delsNumRecUP,
        "minNumRecTail" to Recursion::minNumRecTail,
        "kolNum3RecTail" to Recursion::kolNum3RecTail,
        "delsNumRecTail" to Recursion::delsNumRecTail
    )
    return funcs[name]
}
fun main() {
    val scanner = Scanner(System.`in`)
    val dir: String = scanner.next()
    val pathIn: String = dir + "Request.txt"
    val pathOut: String = dir + "Answer.txt"
    FileInDirectory(pathIn)
    File(pathOut).writeText("")
    for (row in arr){
        val func = getFunc(row.last())
        println(func)
        if (row.size == 2)
            File(pathOut).appendText((row[0]+" "+row[1]+" "+func!!.call(row[0].toInt())+"\n").toString())
        if (row.size == 3)
            File(pathOut).appendText((row[0]+" "+row[1]+" "+row[2]+" "+func!!.call(row[0].toInt(), row[1].toInt())+"\n").toString())
        if (row.size == 4)
            File(pathOut).appendText((row[0]+" "+row[1]+" "+row[2]+" "+row[3] +" "+func!!.call(row[0].toInt(), row[1].toInt(), row[2].toInt())+"\n").toString())
    }
}