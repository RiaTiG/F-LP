import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class Kotlin_Lab5_FuncTest {

    @Test
    fun minNum() {
        val main = Kotlin_Lab5_Func()
        val expected = 1
        assertEquals(expected, main.minNum(346198))
    }

    @Test
    fun kolNum3() {
        val main = Kotlin_Lab5_Func()
        val expected = 2
        assertEquals(expected, main.kolNum3(4812357))
    }

    @Test
    fun delsNum() {
        val main = Kotlin_Lab5_Func()
        val expected = 6
        assertEquals(expected, main.delsNum(12))
    }

    @Test
    fun minNumRecUP() {
        val main = Kotlin_Lab5_Func()
        val expected = 1
        assertEquals(expected, main.minNumRecUP(346198))
    }

    @Test
    fun kolNum3RecUP() {
        val main = Kotlin_Lab5_Func()
        val expected = 2
        assertEquals(expected, main.kolNum3RecUP(48123))
    }

    @Test
    fun delsNumRecUP() {
        val main = Kotlin_Lab5_Func()
        val expected = 6
        assertEquals(expected, main.delsNumRecUP(12,1))
    }

    @Test
    fun minNumRecTail() {
        val main = Kotlin_Lab5_Func()
        val expected = 1
        assertEquals(expected, main.minNumRecTail(346198,9))
    }

    @Test
    fun kolNum3RecTail() {
        val main = Kotlin_Lab5_Func()
        val expected = 2
        assertEquals(expected, main.kolNum3RecTail(4812357,0))
    }

    @Test
    fun delsNumRecTail() {
        val main = Kotlin_Lab5_Func()
        val expected = 6
        assertEquals(expected, main.delsNumRecTail(12,1,0))
    }
}