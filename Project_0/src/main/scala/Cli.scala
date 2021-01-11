import scala.io.StdIn
import scala.util.matching.Regex 
import java.io.FileNotFoundException

class Cli{
    var location = "Beach"
    var inventory = new Array[String](4)
    var shedStat = new Array[Int](4)

    val commandArg: Regex = "(\\w+)\\s*(.*)".r

    def printWelcome() : Unit = {
        println("You wake up on an empty beach. Looking around there is no one to be found. It looks like no one has ever been here before.")
        println("What will you do?")
    }

    def printOptions(): Unit ={
        println("Actions: ")
        // print CSV File, has action words on it...
        val filename = "src/main/scala/csvFile.csv"
        for(line <- Source.fromFile(filename).getLines){
            println(line)
        }
    }

    def pickup(): Unit ={

    }

    def menu(): Unit = {
        printWelcome()
        var continueMenuLoop = true
        while (continueMenuLoop) {
            printOptions()
            val input = StdIn.readLine()

            input match {
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Look") => {
                    lookArg()
                }
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Move") => {
                   moveArg()

                }
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Use") => {
                    shedStatus()
                    
                }
            }
        }

    }
    class lookArg{
        //look around area
        //need to search areas for items,
        //if items were found, there is nothing to pickup there 
        //items will be stored in inventory, if item is in inventory, area is empty
        if (location = "beach"){
            println("An empty beach. Looks like no one has been here before.")
        }
        if (location = "Forest"){
            if (inventory(0) = "Bolt Cutters"){
                println("An overgrown forest. There is so much greenery here, you can't even see the sky or ground.")
            }else{
                inventory(0) = "Bolt Cutters"
                println("You've found Bolt Cutters!")
            }
        }
        if (location = "Shed"){
            if (inventory(1) = "Net"){
                println("It is an old shed... I wonder who built it?")
            }else{
                inventory(1) = "Net"
                println("You've found a net!")
            }

        }
        if (location = "Waterfall"){
            if (inventory(2) = "Crowbar"){
                println("A not so big waterfall but a waterfall none of the less. It is quite peaceful here.")
            }else{
                inventory(2) = "Crowbar"
                println("You've found a Crowbar!")
            }

        }
        if (location = "Cliff"){
            if (inventory(3) = "Key"){
                println("That's a long way down... Better keep my distance.")
            }else{
                inventory(3) = "Key"
                println("You've found a Key!")
            }

        }else{
            locationError()
        }
    }
    class moveArg{
        //move to another area
        //areas: Beach (start), Forest, Shed, Waterfall, Cliff
        if (location = "beach"){

        }
        if (location = "Forest"){

        }
        if (location = "Shed"){

        }
        if (location = "Waterfall"){

        }
        if (location = "Cliff"){

        }else{
            locationError()
        }
    }

    class useArg{
        println("What would you like to use?")
        println(inventory)

    }

    class shedStatus{
        if (shedStat.contains(arg)){
            
        }

    }




    class locationError{
        println("Location not found... Please select an area")
        menu()
    }
}
