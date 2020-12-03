package main.kotlin.avlTree

import kotlin.math.max

class AVLTree {
    var root: Node? = null

    fun height(x: Node?, y: Node?) =
        if (x == null && y == null) 0
        else if (x == null) y!!.height
        else if (y == null) x.height
        else max(x.height, y.height)

    fun balance(x: Node?, y: Node?) =
        if (x == null && y == null) 0
        else if (x == null) -y!!.height
        else if (y == null) x.height
        else x.height - y.height

    fun addByKey(node: Node?, key: Int, value: Int, dad: Node?): Node {
        if (node == null) {
            return Node(key, value, dad)
        }
        val compareRes = key.compareTo(node.key)
        if (compareRes > 0) {
            node.right = addByKey(node.right, key, value, node)
            node.height = height(node.left, node.right) + 1
        } else if (compareRes < 0) {
            node.left = addByKey(node.left, key, value, node)
            node.height = height(node.left, node.right) + 1
        } else {
            node.value = value
        }
        return node
    }
}