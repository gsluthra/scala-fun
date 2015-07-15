package fpbook

import scala.util.Random

object TreeFun {

  sealed trait Tree[+A]

  case class Node[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]
  case class Leaf[A](value: A) extends Tree[A]

  def countNodes[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(_)                      => 1
      case Node(_, leftNode, rightNode) => 1 + countNodes(leftNode) + countNodes(rightNode)
    }
  }


  def createDummyTree: Node[String] = {
    val tree = Node[String]("Root",
      Node[String]("left-subtree", generateRandomLeafNode, generateRandomLeafNode),
      Node[String]("right-rightsubtree", generateRandomLeafNode, generateRandomLeafNode))

    tree
  }

  private def generateRandomLeafNode: Leaf[String] = Leaf[String]("RandomLeaf_" + Random.nextInt(1000))


  def main(args: Array[String]) {
    println("The TREE Program")

    val myTree = createDummyTree
    println(myTree)
    println("Count of Nodes: " + countNodes(myTree))
  }
}