import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

data class DrivingLicense(
    val series: String,
    val number: String,
    val issueDate: LocalDate
) : Comparable<DrivingLicense> {

    init {
        val seriesPattern = Pattern.compile("^\\d{4}$")
        val numberPattern = Pattern.compile("^\\d{6}$")
        require(seriesPattern.matcher(series).matches()) { "Неправильный формат серии" }
        require(numberPattern.matcher(number).matches()) { "Неправильный формат номера" }
    }

    fun display() {
        println("Водительские права(Серия='$series', Номер='$number', Дата выдачи=${issueDate.format(DateTimeFormatter.ISO_LOCAL_DATE)})")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DrivingLicense) return false
        return series == other.series && number == other.number
    }

    override fun hashCode(): Int {
        return 31 * series.hashCode() + number.hashCode()
    }

    override fun compareTo(other: DrivingLicense): Int {
        return this.issueDate.compareTo(other.issueDate)
    }

    companion object {
        val SeriesNumberComparator = Comparator<DrivingLicense> { d1, d2 ->
            val seriesComp = d1.series.compareTo(d2.series)
            if (seriesComp != 0) seriesComp else d1.number.compareTo(d2.number)
        }
    }
}

fun main() {
    val license1 = DrivingLicense("9938", "061529", LocalDate.of(2020, 5, 20))
    val license4 = DrivingLicense("9938", "061529", LocalDate.of(2020, 5, 20))
    val license2 = DrivingLicense("1838", "654321", LocalDate.of(2018, 3, 18))
    val license3 = DrivingLicense("1445", "123457", LocalDate.of(2021, 7, 23))

    license1.display()
    license2.display()
    license3.display()
    license4.display()

    println("Документ1 == Документ2: ${license1 == license2}")
    println("Документ1 == Документ3: ${license1 == license3}")

    val hashSet = hashSetOf(license1, license2, license3, license1)
    println("HashSet: ")
    hashSet.forEach { println(it) }

    val treeSetByDate = sortedSetOf(license1, license2, license3)
    println("TreeSet по дате выдачи: ")
    treeSetByDate.forEach { println(it) }

    val treeSetBySeriesNumber = sortedSetOf(DrivingLicense.SeriesNumberComparator, license1, license2, license3)
    println("TreeSet по серия-номер: ")
    treeSetBySeriesNumber.forEach { println(it) }
}
