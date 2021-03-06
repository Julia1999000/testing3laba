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

    fun addJust(nodeAVL: Node?, key: Int, value: Int?, dad: Node?): Node {
        var node: Node = nodeAVL ?: return Node(key, value!!, dad)
        val compareResult = key.compareTo(node.key)
        when {
            compareResult > 0 -> {
                node.right = addJust(node.right, key, value, node)
                node.height = height(node.left, node.right) + 1
            }
            compareResult < 0 -> {
                node.left = addJust(node.left, key, value, node)
                node.height = height(node.left, node.right) + 1
            }
            else -> {
                node.value = value!!
            }
        }
        node.balance = balance(node.left, node.right)
        if (node.balance == -2) {
            node = leftRotation(node)!!
        } else if (node.balance == 2) {
            node = rightRotation(node)!!
        }
        return node
    }

    fun add(key: Int, value: Int?) {
        this.root = addJust(this.root, key, value, null)
    }


    fun getJust(nodeAVL: Node?, key: Int): Int? {
        if (nodeAVL == null) {
            return null
        }
        val compareResult = key.compareTo(nodeAVL.key)
        return when {
            compareResult == 0 -> {
                nodeAVL.value
            }
            compareResult > 0 -> {
                getJust(nodeAVL.right, key)
            }
            else -> {
                getJust(nodeAVL.left, key)
            }
        }
    }

    fun get(key: Int): Int? {
        return getJust(root, key)
    }

    fun min(nodeAVL: Node?): Node? {
        return if (nodeAVL!!.left == null) nodeAVL else min(nodeAVL.left)
    }

    fun max(nodeAVL: Node?): Node? {
        return if (nodeAVL!!.right == null) nodeAVL else max(nodeAVL.right)
    }


    fun deleteJust(nodeAVL: Node?, key: Int): Node? {
        var node: Node? = nodeAVL ?: return null
        val compareResult = key.compareTo(node!!.key)
        if (compareResult > 0) {
            node.right = deleteJust(node.right, key)
        } else if (compareResult < 0) {
            node.left = deleteJust(node.left, key)
        } else {
            if (node.right == null && node.left == null) {
                node = null
            } else if (node.right == null) {
                node.left!!.dad = node.dad
                node = node.left
            } else if (node.left == null) {
                node.right!!.dad = node.dad
                node = node.right
            } else {
                if (node.right!!.left == null) {
                    node.right!!.left = node.left
                    node.right!!.dad = node.dad
                    node.right!!.dad = node.dad
                    node.left!!.dad = node.right
                    node = node.right
                } else {
                    val res = min(node.right)
                    node.key = res!!.key
                    node.value = res.value
                    deleteJust(node.right, node.key)
                }
            }
        }
        if (node != null) {
            node.height = height(node.left, node.right) + 1
            node.balance = balance(node.left, node.right)
            if (node.balance == -2) {
                node = leftRotation(node)
            } else if (node.balance == 2) {
                node = rightRotation(node)
            }
        }
        return node
    }

    fun delete(key: Int) {
        root = deleteJust(root, key)
    }
}