package comparer

import scala.tools.nsc.{Global, Phase}
import scala.tools.nsc.plugins.PluginComponent

class TypeComparerComponent(val global: Global) extends PluginComponent {
  import global._
  val runsAfter = List[String]("typer")
  val phaseName = "type comparer component"
  def newPhase(_prev: Phase) = new TypeComparerPhase(_prev)


  class TypeComparerPhase(prev: Phase) extends StdPhase(prev) {
    override def name = "type comparer"
    def apply(unit: CompilationUnit): Unit = {
      new TypeComparerTraverser().traverse(unit.body)

    }
  }

  private class TypeComparerTraverser extends Traverser {
    override def traverse(tree: global.Tree): Unit =
      tree match {
        case tr @ Apply(Select(left, nme.EQ), List(right)) if (!(left.tpe <:< right.tpe))  =>
            global.reporter.error(tree.pos, s"comparison of variables of different types")
        case _ => super.traverse(tree)
      }
  }
}