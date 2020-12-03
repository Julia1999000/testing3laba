package test

import main.kotlin.avlTree.Node
import org.junit.Assert
import org.junit.Test

internal class AVLTreeTest {

    @Test
    fun ConstructorNodeTest() {
        val node = Node(1, 1, null)

        Assert.assertEquals(node.height.toLong(), 1)
        Assert.assertEquals(node.balance.toLong(), 0)
        Assert.assertEquals(node.key.toLong(), 1)
        Assert.assertEquals(node.value.toLong(), 1)
        Assert.assertNull(node.left)
        Assert.assertNull(node.right)
        Assert.assertNull(node.dad)
    }
}