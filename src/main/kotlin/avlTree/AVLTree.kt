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

    fun leftRotation(nodeAVL: Node?): Node? {
        var node = nodeAVL
        if (node!!.right!!.right == null && node.right!!.left != null) {
            node.right = rightRotation(node.right)
            node = leftRotation(node)
        } else if (node.right!!.left == null || node.right!!.left!!.height <= node.right!!.right!!.height) {
            val newnode = node.right
            newnode!!.dad = node.dad
            node.right = newnode.left
            if (node.right != null) {
                node.right!!.dad = node
            }
            node.height = height(node.left, node.right) + 1
            node.dad = newnode
            node.balance = balance(node.left, node.right)
            newnode.left = node
            node = newnode
            node.balance = balance(node.left, node.right)
            node.height = height(node.left, node.right) + 1
        } else {
            node.right = rightRotation(node.right)
            node = leftRotation(node)
        }
        return node
    }

    fun rightRotation(nodeAVL: Node?): Node? {
        var node = nodeAVL
        if (node!!.left!!.right != null && node.left!!.left == null) {
            node.left = leftRotation(node.left)
            node = rightRotation(node)
        } else if (node.left!!.right == null || node.left!!.right!!.height <= node.left!!.left!!.height) {
            val newnode = node.left
            newnode!!.dad = node.dad
            node.left = newnode.right
            if (node.left != null) {
                node.left!!.dad = node
            }
            node.height = height(node.left, node.right) + 1
            node.dad = newnode
            node.balance = balance(node.left, node.right)
            newnode.right = node
            node = newnode
            node.balance = balance(node.left, node.right)
            node.height = height(node.left, node.right) + 1
        } else {
            node.left = leftRotation(node.left)
            node = rightRotation(node)
        }
        return node
    }
}