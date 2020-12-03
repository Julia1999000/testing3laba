package main.kotlin.avlTree

class Node(var key: Int, var value: Int, var dad: Node?) {
    var height = 1
    var balance = 0
    var left: Node? = null
    var right: Node? = null
}