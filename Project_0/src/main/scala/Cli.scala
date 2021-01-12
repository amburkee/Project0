import scala.io.StdIn
import scala.util.matching.Regex 
import java.io.FileNotFoundException
import scala.io.Source

class Cli{
    var location : String = "Beach"
    var inventory = new Array[String](4)
    var shedStat = new Array[String](4)

    val commandArg: Regex = "(\\w+)\\s*(.*)".r

    def printOptions(): Unit ={
        // print CSV File, has action words on it...
        val filename = "src/main/scala/csvFile.csv"
        for(line <- Source.fromFile(filename).getLines){
            println(line)
        }
    }

    def pickup(): Unit ={

    }

    def menu(): Unit = {
        var continueMenuLoop = true
        while (continueMenuLoop) {
            printOptions
            val input = StdIn.readLine()

            input match {
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Look") => {
                    lookArg
                }
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Move") => {
                    if (arg == location){
                        locationNotAvailable
                    }else{
                        moveArg
                    } 
                }
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Use") => {
                    if (shedStat.contains(arg)){

                    }
                    shedStatus
                    
                }
            }
        }

    }

    def lookArg{
        //look around area
        //need to search areas for items,
        //if items were found, there is nothing to pickup there 
        //items will be stored in inventory, if item is in inventory, area is empty
        if (location == "beach"){
            println("An empty beach. Looks like no one has been here before.")
        }
        if (location == "Forest"){
            if (inventory(0) == "Bolt Cutters"){
                println("An overgrown forest. There is so much greenery here, you can't even see the sky or ground.")
            }else{
                inventory(0) == "Bolt Cutters"
                println("You've found Bolt Cutters!")
            }
        }
        if (location == "Shed"){
            if (inventory(1) == "Net"){
                println("It is an old shed... I wonder who built it?")
            }else{
                inventory(1) == "Net"
                println("You've found a net!")
            }

        }
        if (location == "Waterfall"){
            if (inventory(2) == "Crowbar"){
                println("A not so big waterfall but a waterfall none of the less. It is quite peaceful here.")
            }else{
                inventory(2) = "Crowbar"
                println("You've found a Crowbar!")
            }

        }
        if (location == "Cliff"){
            if (inventory(3) == "Key"){
                println("That's a long way down... Better keep my distance.")
            }else{
                inventory(3) = "Key"
                println("You've found a Key!")
            }

        }else{
            locationError
        }
    }
    def moveArg{
        //move to another area
        //areas: Beach (start), Forest, Shed, Waterfall, Cliff
        
    }

    def useArg{
        println("What would you like to use?")
        println(inventory)

    }

    def shedStatus{
        

    }




    def locationError{
        println("Location not found... Please select an area")
        menu()
    }
    def locationNotAvailable{
        println(s"You are already at $location. Please select another location.")
        menu()
    }
}
