import scala.io.StdIn
import scala.util.matching.Regex 
import java.io.FileNotFoundException
import scala.io.Source

object Main extends App {
  val newGame = new Cli()

  println("You wake up on an empty beach. Looking around there is no one to be found. It looks like no one has ever been here before.")
  println("What will you do?")

  newGame.menu()
  
  println("You have done it! You opened the Shed and found a lot of materials inside. You take it all to the beach to put it together.")
  println("Congrats you have escaped the island! Would you like to play again? Y/N")

}