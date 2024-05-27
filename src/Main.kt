// Базовый класс для всех продуктов
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

// Пример использования классов
fun main() {
    val phone = Phone(1, "Galaxy S21", "Samsung", 799.99, "Battery", "Android", 128)
    val laptop = Laptop(2, "MacBook Pro", "Apple", 1299.99, "Battery", 16, "M1")
    val accessory = Accessory(3, "Wireless Charger", "Samsung", 49.99, "Charger")
    val tv = TV(4, "QLED TV", "Samsung", 999.99, "AC", 55.0, true)
    val tablet = Tablet(5, "iPad Pro", "Apple", 799.99, "Battery", "iOS", 256, 12.9)

    println(phone)
    println(laptop)
    println(accessory)
    println(tv)
    println(tablet)
}
