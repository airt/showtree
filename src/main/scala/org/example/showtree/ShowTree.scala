package org.example.showtree

object ShowTree {

  /**
    * Serialize a tree.
    *
    * @param root root of a tree.
    * @return lines.
    */
  def asciiDisplay(root: TreeNode[String]): Seq[String] = {
    addPrefixes(show(root))
  }

  /**
    * Serialize a tree node.
    *
    * @param node tree node.
    * @return lines.
    */
  private[showtree] def show(node: TreeNode[String]): Seq[String] = node match {
    case TreeNode(data, children) =>
      data +: showChildren(children)
  }

  /**
    * Serialize children of a tree node.
    *
    * @param nodes children of a tree node.
    * @return lines.
    */
  private[showtree] def showChildren(nodes: Seq[TreeNode[String]]): Seq[String] = nodes match {
    case Nil =>
      Nil
    case one +: Nil =>
      addPrefixes(show(one))
    case one +: others =>
      addPrefixes(show(one), isLast = false) ++ showChildren(others)
  }

  /**
    * Add prefixes to lines serialized from a tree node.
    *
    * @param lines  lines serialized from a tree node.
    * @param isLast whether this node is the last child of its parent tree node.
    * @return lines after processing.
    */
  private[showtree] def addPrefixes(lines: Seq[String], isLast: Boolean = true): Seq[String] = {
    val dataLine +: childrenLines = lines
    val dataLineWithPrefix = "+-" + dataLine
    val childrenLinesWithPrefixes =
      if (childrenLines.isEmpty) Nil
      else if (isLast) childrenLines.map("  " + _)
      else (childrenLines :+ "").map("| " + _)
    dataLineWithPrefix +: childrenLinesWithPrefixes
  }

}
