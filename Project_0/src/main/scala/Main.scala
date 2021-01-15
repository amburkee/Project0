import scala.io.StdIn
import scala.util.matching.Regex 
import java.io.FileNotFoundException
import scala.io.Source


object Main extends App {
  val newGame = new Cli()

  println("You wake up on an empty beach. Looking around there is no one to be found. It looks like no one has ever been here before.")
  println("What will you do?")

  newGame.menu()

  println("Would you like to exit completely or play again?")
  println("Play -> Play again")
  println("Exit -> Exit game")
  val reset: Regex = "(\\w+)".r
  val input = StdIn.readLine()
  input match {
      case reset(cmd) if cmd.equalsIgnoreCase("play") => {
          newGame.menu()
      }
      case reset(cmd) if cmd.equalsIgnoreCase("exit") => {
          println("Goodbye!")
      }
  }

}