package org.example.showtree

import org.scalatest._

class ShowTreeSpec extends FlatSpec with Matchers {

  import ShowTree._

  "A tree node" should "display as ascii" in {
    val root =
      TreeNode(
        "root",
        List(
          TreeNode("level-1-1"),
          TreeNode("level-1-2"),
          TreeNode("level-1-3")
        )
      )
    asciiDisplay(root) mkString "\n" should be(
      """+-root
        |  +-level-1-1
        |  +-level-1-2
        |  +-level-1-3
      """.stripMargin.trim
    )
  }

  it should "display as ascii recursively" in {
    val root =
      TreeNode(
        "root",
        List(
          TreeNode(
            "level-1-1",
            List(
              TreeNode(
                "level-2-1",
                List(
                  TreeNode("level-3-1")
                )
              )
            )
          ),
          TreeNode("level-1-2"),
          TreeNode("level-1-3")
        )
      )
    asciiDisplay(root) map (_ stripSuffix " ") mkString "\n" should be(
      """+-root
        |  +-level-1-1
        |  | +-level-2-1
        |  |   +-level-3-1
        |  |
        |  +-level-1-2
        |  +-level-1-3
      """.stripMargin.trim
    )
  }

  it should "display as ascii flexibly" in {
    val root =
      TreeNode(
        "root",
        List(
          TreeNode(
            "level-1-1",
            List(
              TreeNode(
                "level-2-1",
                List(
                  TreeNode("level-3-1")
                )
              ),
              TreeNode("level-2-2")
            )
          ),
          TreeNode("level-1-2"),
          TreeNode("level-1-3")
        )
      )
    asciiDisplay(root) map (_ stripSuffix " ") mkString "\n" should be(
      """+-root
        |  +-level-1-1
        |  | +-level-2-1
        |  | | +-level-3-1
        |  | |
        |  | +-level-2-2
        |  |
        |  +-level-1-2
        |  +-level-1-3
      """.stripMargin.trim
    )
  }

  "Lines" should "be added prefixes correctly" in {
    val lines =
      """root
        |level-1-1
        |level-1-2
      """.stripMargin.trim.lines.toSeq
    addPrefixes(lines, isLast = false) map (_ stripSuffix " ") mkString "\n" should be(
      """+-root
        || level-1-1
        || level-1-2
        ||
      """.stripMargin.trim
    )
  }

  it should "be added prefixes correctly when come from the last child of its parent" in {
    val lines =
      """root
        |level-1-1
        |level-1-2
      """.stripMargin.trim.lines.toSeq
    addPrefixes(lines, isLast = true) mkString "\n" should be(
      """+-root
        |  level-1-1
        |  level-1-2
      """.stripMargin.trim
    )
  }

}
