data class TreeNode(val value: String, var left: TreeNode? = null, var right: TreeNode? = null)
class BinaryTree {
    var root: TreeNode? = null
    fun insert(value: String, comparator: (String, String) -> Int) {
        root = recInsert(root, value, comparator)
    }

    private fun recInsert(current: TreeNode?, value: String, comparator: (String, String) -> Int): TreeNode {
        if (current == null) {
            return TreeNode(value)
        }
        if (comparator(value, current.value) < 0) {
            current.left = recInsert(current.left, value, comparator)
        } else {
            current.right = recInsert(current.right, value, comparator)
        }
        return current
    }

    fun sortedTree(): List<String> {
        val result = mutableListOf<String>()
        recTree(root, result)
        return result
    }

    private fun recTree(node: TreeNode?, result: MutableList<String>) {
        if (node != null) {
            recTree(node.left, result)
            result.add(node.value)
            recTree(node.right, result)
        }
    }
}

fun listToBinaryTree(list: List<String>, comparator: (String, String) -> Int): BinaryTree {
    val binaryTree = BinaryTree()
    for (value in list) {
        binaryTree.insert(value, comparator)
    }
    return binaryTree
}

fun binaryTreeToList(binaryTree: BinaryTree): List<String> {
    return binaryTree.sortedTree()
}

fun main() {
    val stringList = listOf(
        "Kotlin это круто",
        "Kotlin это не просто круто? а круто круто",
        "Мне нравится Kotlin",
        "Деревья и в лесу и в котлине"
    )

    val comparator: (String, String) -> Int = { a, b -> a.split(" ").size.compareTo(b.split(" ").size) }
    val binaryTree = listToBinaryTree(stringList, comparator)
    val sortedList = binaryTreeToList(binaryTree)
    println("Отсортированный список по кол-ву слов:")
    sortedList.forEach { println(it) }
}