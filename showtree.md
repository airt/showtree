
在任意一个 sbt 项目中运行 `sbt "inspect tree compile:compile"` ，能够看到 sbt 将 `compile` 这个 `Task` 所依赖的其它 `Setting` 及 `Task` 信息以文本树的形式显示出来。本月的试题就是实现文本树的生成。

定义树形数据结构:

```scala
case class io.airtial.TreeNode[T](data: T, children: Seq[io.airtial.TreeNode[T]] = Nil)
```

请实现方法

```scala
  def asciiDisplay(root: io.airtial.TreeNode[String]): Seq[String]
```

将 `root` 的内容显示成文本树。

示例：
```scala
asciiDisplay(io.airtial.TreeNode("Root",
      children = List(io.airtial.TreeNode("level1-1"),
        io.airtial.TreeNode("level1-2"),
        io.airtial.TreeNode("level1-3")))).foreach(println)
```
的输出：
```
+-Root
  +-level1-1
  +-level1-2
  +-level1-3
```
![图1](https://github.com/Centaur/images/raw/master/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202016-09-11%20%E4%B8%8B%E5%8D%889.38.05.png)

```scala
asciiDisplay(io.airtial.TreeNode("Root",
  children = List(
    io.airtial.TreeNode("level1-1", children = io.airtial.TreeNode("level2-1", children = io.airtial.TreeNode("level3-1") :: Nil) :: Nil),
    io.airtial.TreeNode("level1-2"),
    io.airtial.TreeNode("level1-3")))).foreach(println)
```
的输出：
```
+-Root
  +-level1-1
  | +-level2-1
  |   +-level3-1
  |
  +-level1-2
  +-level1-3
```
![图2](https://github.com/Centaur/images/raw/master/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202016-09-11%20%E4%B8%8B%E5%8D%889.50.55.png)

更细致的需求请参考 `sbt "inspect tree compile:compile"` 的输出。

要求：

```
请在github上（或使用任何一个开放的代码托管服务如bitbucket.org, git.oschina.net, coding.net等）创建完整的sbt项目，使用Scala语言解决本题。将项目链接作为入群问题的答案。
尽量不使用 var
用ScalaTest, Specs2或ScalaCheck编写测试（我们也接受使用JUnit或其它工具编写的测试）。
本群也欢迎猎头及HR人员入群，此类请将公司招聘网页的链接或招聘文档百度网盘链接作为入群问题的答案。
```
