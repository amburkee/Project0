import scala.io.Source

object Main extends App {
  println("You wake up on an empty beach. Looking around there is no one to be found. It looks like no one has ever been here before.")
  println("What will you do?")
  // print CSV File, has action words on it...
  val filename = "src/main/scala/csvFile.csv"

  for(line <- Source.fromFile(filename).getLines){
      println(line)
  }
  
  var location = "beach"
  Array inventory["","","",""]
  


  //......

  println("You have done it! You opened the Shed and found a lot of material inside. You go and take it all to the beach to put it " +
    "together.")
  println("Congrats you have escaped the island! Would you like to play again? Y/N")

}