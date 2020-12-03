package test

import main.kotlin.avlTree.AVLTree
import main.kotlin.avlTree.Node
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

internal class AVLTreeTest {

    @Test
    fun constructorNodeTest() {
        val node = Node(1, 1, null)

        Assert.assertEquals(node.height.toLong(), 1)
        Assert.assertEquals(node.balance.toLong(), 0)
        Assert.assertEquals(node.key.toLong(), 1)
        Assert.assertEquals(node.value.toLong(), 1)
        Assert.assertNull(node.left)
        Assert.assertNull(node.right)
        Assert.assertNull(node.dad)
    }

    @Test
    fun heightAVLTreeTest() {
        val avl = AVLTree()

        val node1 = Node(1, 1, null)
        val node2 = Node(2, 2, null)
        assertEquals(avl.height(node1, node2), 1)

        node1.height = 3
        assertEquals(avl.height(node1, node2), 3)

        assertEquals(avl.height(null, null), 0)

        assertEquals(avl.height(node1, null), 3)

        assertEquals(avl.height(null, node2), 1)
    }

    @Test
    fun balanceAVLTreeTest() {
        val avl = AVLTree()

        val node1 = Node(1, 1, null)
        val node2 = Node(2, 2, null)
        assertEquals(avl.balance(node1, node2), 0)

        node1.height = 3
        assertEquals(avl.balance(node1, node2), 2)

        assertEquals(avl.balance(null, null), 0)

        assertEquals(avl.balance(node1, null), 3)

        assertEquals(avl.balance(null, node2), -1)
    }

    @Test
    fun addByKeyAVLTreeTest() {
        val avl = AVLTree()

        var root: Node = avl.addByKey(null, 2, 2, null)
        Assert.assertNotNull(root)

        root = avl.addByKey(root, 3, 3, null)
        assertEquals(root.right!!.key, 3)

        root = avl.addByKey(root, 1, 1, null)
        assertEquals(root.left!!.key, 1)

        root = avl.addByKey(root, 1, 1, null)
        assertEquals(root.left!!.key, 1)

        root = avl.addByKey(root, 0, 0, null)
        assertEquals(root.left!!.left!!.key, 0)
    }
}