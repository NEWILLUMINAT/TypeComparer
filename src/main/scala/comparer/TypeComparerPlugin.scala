package comparer


import scala.tools.nsc.Global
import scala.tools.nsc.plugins.{Plugin, PluginComponent}

class TypeComparerPlugin(val global: Global) extends Plugin {

  import global._

  val name = "typecomparer"
  val description = "checks types for comparison operation"
  val components = List[PluginComponent](new TypeComparerComponent(this.global))
}
