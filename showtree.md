[TOC]

[原文](https://bitbucket.org/snippets/centaur/qojRG)

在任意一个 sbt 项目中运行 `sbt "inspect tree compile:compile"`, 能够看到 sbt 将 `compile` 这个 `Task` 所依赖的其它 `Setting` 及 `Task` 信息以文本树的形式显示出来. 本题就是实现文本树的生成.

定义树形数据结构:

```scala
case class TreeNode[T](data: T, children: Seq[TreeNode[T]] = Nil)
```

请实现方法

```scala
def asciiDisplay(root: TreeNode[String]): Seq[String]
```

将 `root` 的内容显示成文本树.

示例:

```scala
val root =
  TreeNode("root", List(
    TreeNode("level-1-1"),
    TreeNode("level-1-2"),
    TreeNode("level-1-3")
  ))
asciiDisplay(root).foreach(println)
```

的输出:

```
+-root
  +-level-1-1
  +-level-1-2
  +-level-1-3
```

```scala
val root =
  TreeNode("root", List(
    TreeNode("level-1-1", List(
      TreeNode("level-2-1", List(
        TreeNode("level-3-1")
      ))
    )),
    TreeNode("level-1-2"),
    TreeNode("level-1-3")
  ))
asciiDisplay(root).foreach(println)
```

的输出:

```
+-root
  +-level-1-1
  | +-level-2-1
  |   +-level-3-1
  |
  +-level-1-2
  +-level-1-3
```

更细致的需求请参考 `sbt "inspect tree compile:compile"` 的输出.

要求:

```
请在 github 上 (或使用任何一个开放的代码托管服务如 bitbucket.org, git.oschina.net, coding.net 等) 创建完整的 sbt 项目, 使用 Scala 语言解决本题.
尽量不使用 var.
用 ScalaTest, Specs2 或 ScalaCheck 编写测试 (也可以使用 JUnit 或其它工具编写的测试).
```
