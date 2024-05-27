import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File

data class StoreContainer(val Electronic: List<Product>)
open class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val price: Double
)

// Наследуемся от Product для устройств с общими характеристиками
open class ElectronicDevice(
    id: Int,
    name: String,
    brand: String,
    price: Double,
    val power: String // например, AC, DC, Battery
) : Product(id, name, brand, price)

// Класс для телефонов
class Phone(
    id: Int,
    name: String,
    brand: String,
    price: Double,
    power: String,
    val os: String,     // Операционная система
    val storage: Int    // Объем памяти в ГБ
) : ElectronicDevice(id, name, brand, price, power) {
    override fun toString(): String {
        return "Phone(id=$id, name='$name', brand='$brand', price=$price, power='$power', os='$os', storage=$storage)"
    }
}

// Класс для ноутбуков
class Laptop(
    id: Int,
    name: String,
    brand: String,
    price: Double,
    power: String,
    val ram: Int,       // Объем оперативной памяти в ГБ
    val processor: String // Тип процессора
) : ElectronicDevice(id, name, brand, price, power) {
    override fun toString(): String {
        return "Laptop(id=$id, name='$name', brand='$brand', price=$price, power='$power', ram=$ram, processor='$processor')"
    }
}

// Класс для аксессуаров (например, наушники, зарядные устройства и т.д.)
class Accessory(
    id: Int,
    name: String,
    brand: String,
    price: Double,
    val type: String  // Тип аксессуара, например, зарядка, чехол и т.д.
) : Product(id, name, brand, price) {
    override fun toString(): String {
        return "Accessory(id=$id, name='$name', brand='$brand', price=$price, type='$type')"
    }
}

// Класс для телевизоров
class TV(
    id: Int,
    name: String,
    brand: String,
    price: Double,
    power: String,
    val screenSize: Double, // Размер экрана в дюймах
    val isSmart: Boolean    // Наличие смарт-функций
) : ElectronicDevice(id, name, brand, price, power) {
    override fun toString(): String {
        return "TV(id=$id, name='$name', brand='$brand', price=$price, power='$power', screenSize=$screenSize, isSmart=$isSmart)"
    }
}

// Класс для планшетов
class Tablet(
    id: Int,
    name: String,
    brand: String,
    price: Double,
    power: String,
    val os: String,     // Операционная система
    val storage: Int,   // Объем памяти в ГБ
    val screenSize: Double // Размер экрана в дюймах
) : ElectronicDevice(id, name, brand, price, power) {
    override fun toString(): String {
        return "Tablet(id=$id, name='$name', brand='$brand', price=$price, power='$power', os='$os', storage=$storage, screenSize=$screenSize)"
    }
}

//Функция для сериализации списка объектов
fun <Any> serializeToJsonFile(fileName: String, items: List<Any>) {
    val gson = Gson()
    val json = gson.toJson(items)
    File(fileName).writeText(json)
}
//Функция для десериализациия объектов
fun deserializeJsonFilePhone(filePath: String):List<Phone> {
    val jsonString = File(filePath).readText()
    val listType = object : TypeToken<List<Phone>>() {}.type
    val items: List<Phone> = Gson().fromJson(jsonString, listType)
    println("Десериализованный список объектов:")
    for (item in items) {
        println(item.toString())}
    return items
}
fun deserializeJsonFileLaptop(filePath: String):List<Laptop> {
    val jsonString = File(filePath).readText()
    val listType = object : TypeToken<List<Laptop>>() {}.type
    val items: List<Laptop> = Gson().fromJson(jsonString, listType)
    println("Десериализованный список объектов:")
    for (item in items) {
        println(item.toString())}
    return items
}
fun deserializeJsonFileAccess(filePath: String):List<Accessory> {
    val jsonString = File(filePath).readText()
    val listType = object : TypeToken<List<Accessory>>() {}.type
    val items: List<Accessory> = Gson().fromJson(jsonString, listType)
    println("Десериализованный список объектов:")
    for (item in items) {
        println(item.toString())}
    return items
}
fun deserializeJsonFileTv(filePath: String):List<TV> {
    val jsonString = File(filePath).readText()
    val listType = object : TypeToken<List<TV>>() {}.type
    val items: List<TV> = Gson().fromJson(jsonString, listType)
    println("Десериализованный список объектов:")
    for (item in items) {
        println(item.toString())}
    return items
}
fun deserializeJsonFileTablet(filePath: String):List<Tablet> {
    val jsonString = File(filePath).readText()
    val listType = object : TypeToken<List<Tablet>>() {}.type
    val items: List<Tablet> = Gson().fromJson(jsonString, listType)
    println("Десериализованный список объектов:")
    for (item in items) {
        println(item.toString())}
    return items
}
// Пример использования классов
fun main() {
    val phones = listOf(
        Phone(1, "Galaxy S21", "Samsung", 799.99, "Battery", "Android", 128),
        Phone(2, "iPhone 12", "Apple", 899.99, "Battery", "iOS", 64),
        Phone(3, "Pixel 5", "Google", 699.99, "Battery", "Android", 128),
        Phone(4, "Galaxy S20", "Samsung", 749.99, "Battery", "Android", 128),
        Phone(5, "iPhone SE", "Apple", 399.99, "Battery", "iOS", 128)
    )

    val laptops = listOf(
        Laptop(1, "MacBook Pro", "Apple", 1299.99, "Battery", 16, "M1"),
        Laptop(2, "XPS 15", "Dell", 1199.99, "Battery", 16, "Intel Core i7"),
        Laptop(3, "Pavilion 15", "HP", 699.99, "Battery", 8, "Intel Core i5"),
        Laptop(4, "Yoga 920", "Lenovo", 999.99, "Battery", 16, "Intel Core i7"),
        Laptop(5, "ROG Zephyrus G14", "Asus", 1449.99, "Battery", 16, "AMD Ryzen 9")
    )
    val accessories = listOf(
        Accessory(1, "Wireless Charger", "Samsung", 49.99, "Charger"),
        Accessory(2, "Phone Case", "Apple", 19.99, "Case"),
        Accessory(3, "Headphones", "Sony", 299.99, "Audio"),
        Accessory(4, "External SSD", "Samsung", 129.99, "S torage"),
        Accessory(5, "HDMI Cable", "Belkin", 14.99, "Cable")
    )

    val tvs = listOf(
        TV(1, "QLED TV", "Samsung", 999.99, "AC", 55.0, true),
        TV(2, "OLED TV", "LG", 1599.99, "AC", 65.0, true),
        TV(3, "LED TV", "Sony", 499.99, "AC", 50.0, false),
        TV(4, "NanoCell TV", "LG", 1199.99, "AC", 75.0, true),
        TV(5, "P Series Quantum", "Vizio", 899.99, "AC", 55.0, true)
    )

    val tablets = listOf(
        Tablet(1, "iPad Pro", "Apple", 799.99, "Battery", "iOS", 256, 12.9),
        Tablet(2, "Galaxy Tab S7", "Samsung", 649.99, "Battery", "Android", 128, 11.0),
        Tablet(3, "MediaPad M5", "Huawei", 349.99, "Battery", "Android", 128, 10.8),
        Tablet(4, "Surface Pro X", "Microsoft", 999.99, "Battery", "Windows", 512, 13.0),
        Tablet(5, "Fire HD 10", "Amazon", 149.99, "Battery", "Fire OS", 64, 10.1)
    )
    serializeToJsonFile("phones.json",phones)
    serializeToJsonFile("laptops.json",laptops)
    serializeToJsonFile("accessories.json",accessories)
    serializeToJsonFile("tvs.json",tvs)
    serializeToJsonFile("tablets.json",tablets)

    val phone: List<Phone> = deserializeJsonFilePhone("phones.json")
    val laptop: List<Laptop> = deserializeJsonFileLaptop("laptops.json")
    val access: List<Accessory> = deserializeJsonFileAccess("accessories.json")
    val tv: List<TV> = deserializeJsonFileTv("tvs.json")
    val tablet: List<Tablet> = deserializeJsonFileTablet("tablets.json")

    val query = mutableListOf<String>()

    val cheapest= phone.minByOrNull { it.price }
    if (cheapest != null) {
        query.add(cheapest.toString())
    } else {
        query.add("Список пуст.")
    }

    val widest= tv.maxByOrNull { it.screenSize }
    if (widest != null) {
        query.add(widest.toString())
    } else {
        query.add("Список пуст.")
    }

    val minRam = laptop.minByOrNull { it.ram }
    if (minRam != null) {
        query.add(minRam.toString())
    } else {
        query.add("Список пуст.")
    }

    val expensiveAccessory = accessories.maxByOrNull { it.price }
    if (expensiveAccessory != null) {
        query.add(expensiveAccessory.toString())
    } else {
        query.add("Список пуст.")
    }


    val biggestStorageTablet = tablet.maxByOrNull { it.storage }
    if (biggestStorageTablet != null) {
        query.add(biggestStorageTablet.toString())
    } else {
        query.add("Список пуст.")
    }

    //задание 6 и 7 выгрузить отчёты в excel
    val filePath="result.xlsx"

    val workbook = XSSFWorkbook()

    // Создаем новый лист
    val sheet = workbook.createSheet("Query Results")

    // Создаем заголовок столбца
    val headerRow = sheet.createRow(0)
    val headerCell = headerRow.createCell(0)
    headerCell.setCellValue("Результаты запросов")

    // Заполняем данные о результатах запросов
    for ((index, result) in query.withIndex()) {
        val row = sheet.createRow(index + 1)
        val cell = row.createCell(0)
        cell.setCellValue(result)
    }

    // Автоматически подгоняем ширину столбца по содержимому
    sheet.autoSizeColumn(0)

    // Сохраняем рабочую книгу в файл
    val outputStream = File(filePath).outputStream()
    workbook.write(outputStream)
    workbook.close()

    println("Результаты запросов сохранены в файл: $filePath")

}