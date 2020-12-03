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

}