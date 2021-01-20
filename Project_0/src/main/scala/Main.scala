import scala.io.StdIn
import scala.util.matching.Regex 
import java.io.FileNotFoundException
import scala.io.Source
import java.sql.DriverManager
import java.sql.Connection
import scala.util.Using



object Main extends App {
  val newGame = new Cli()

  println("You wake up next to a shed. It has chains and wooden planks on it.")
  println("Looking closer there is a note... It says there are supplies inside to escape this deserted island.")
  println("What will you do?")
  println("(You can exit at anytime by typing EXIT)")

  newGame.menu()

  var endGame = true

  while (endGame){
    println("Would you like to exit completely or play again?")
    println("Play -> Play again")
    println("Exit -> Exit game")
    val reset: Regex = "(\\w+)".r
    val input = StdIn.readLine()
    input match {
        case reset(cmd) if cmd.equalsIgnoreCase("play") => {
            val newgame = new Cli()
            println("You wake up on an empty beach. Looking around there is no one to be found. It looks like no one has ever been here before.")
            println("What will you do?")
            newgame.menu()
        }
        case reset(cmd) if cmd.equalsIgnoreCase("exit") => {
            println("Goodbye!")
            endGame = false
        }
        case _ => {
            invalidInput
        }
    }
  }

  def invalidInput {
      println("Invalid input. Try again.")
  }

}