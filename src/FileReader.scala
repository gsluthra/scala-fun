import scala.io.{BufferedSource, Source}

object FileReader{

  def main(args: Array[String]) {
    printFile("resources/Temp.txt")
    printFile("src/FileReader.scala")
  }

  def printFile(filePath:String) {
    val sourceFile = Source.fromFile(filePath, "UTF-8")

    val lines = sourceFile.getLines().toArray;
    lines.map(_.trim).filter(_.size > 0).sortBy(_.size).foreach(println(_))
    println("******************************************")

    sourceFile.close()
  }
}

