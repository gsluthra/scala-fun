package generics

case class Page (modules: Seq[Module])

trait Module {
  def name: String
  def htmlContent: String
}

case class FooterModule(val name: String) extends Module {
  override def htmlContent: String = "<p> Footer Stuff </p>"
}

