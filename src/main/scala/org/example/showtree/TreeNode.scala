package org.example.showtree

case class TreeNode[T](data: T, children: Seq[TreeNode[T]] = Nil)
