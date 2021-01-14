import scala.io.StdIn
import scala.util.matching.Regex 
import java.io.FileNotFoundException
import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class Cli{
    var location : String = "Beach"
    var inventory = new ArrayBuffer[String](4)
    var shedStat = 4

    val commandArg: Regex = "(\\w+)\\s*(.*)".r

    def printOptions(): Unit ={
        // print CSV File, has action words on it...
        val filename = "src/main/scala/csvFile.csv"
        for(line <- Source.fromFile(filename).getLines){
            println(line)
        }
    }

    var continueMenuLoop = true
    def menu(): Unit = {
        while (continueMenuLoop) {
            printOptions
            printInv
            val input = StdIn.readLine()

            input match {
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Look") => {
                    if (arg == null){
                        locationError
                    }
                    lookArg
                }
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Move") => {
                    if (arg == location){
                        
                        locationNotAvailable
                    }
                    if (arg == null){
                            locationError
                    }else{
                        moveArg(arg)
                    } 
                }
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Use") => {
                    if (arg == "Net"){
                        if(location == "Cliff"){
                            println("You got the key!")
                            //add key to inventory array
                        }else{
                            shedError
                        }
                    }
                    if (arg == null){
                        shedError
                    }else {
                        shedStatus(arg) 
                    }  
                }
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Exit") => {
                    continueMenuLoop = false
                    println("Exiting...")
                }
                case _ => {
                    inputError
                }
            }
        }
    }

    def lookArg{
        //look around area
        //need to search areas for items,
        //if items were found, there is nothing to pickup there 
        //items will be stored in inventory, if item is in inventory, area is empty
        if (location.equalsIgnoreCase("beach")){
            println("An empty beach. Looks like no one has been here before.")
        }
        if (location.equalsIgnoreCase("forest")){
            if (inventory(0).equalsIgnoreCase("Bolt Cutters")){
                println("An overgrown forest. There is so much greenery here, you can't even see the sky or ground.")
            }else{
                inventory(0) += "Bolt Cutters"
                println("You've found Bolt Cutters!")
            }
        }
        if (location.equalsIgnoreCase("shed")){
            if (inventory(1) == "Net"){
                println("It is an old shed... I wonder who built it?")
            }else{
                inventory(1) += "Net"
                println("You've found a net!")
            }
        }
        if (location.equalsIgnoreCase("waterfall")){
            if (inventory(2) == "Crowbar"){
                println("A not so big waterfall but a waterfall none of the less. It is quite peaceful here.")
            }else{
                inventory(2) += "Crowbar"
                println("You've found a Crowbar!")
            }
        }
        if (location.equalsIgnoreCase("cliff")){
            if (inventory(3) == "Key"){
                println("That's a long way down... Better keep my distance.")
            }else{
                inventory(3) += "Key"
                println("You've found a Key!")
            }
        }else{
            locationError
        }
    }

    def moveArg(x: String) {
        //move to another area
        //areas: Beach (start), Forest, Shed, Waterfall, Cliff
        if (location == x){
            locationNotAvailable
        }else{
            location = x
            println(s"You move to the $x.")
        }
    }

    def printInv{
        //prints inventory if you don't know what you have
        println("Your Inventory:")
        println(inventory.toString())
    }

    def shedStatus(y: String){
        if (shedStat == 4 && y == "Boltcutters"){
            shedStat = 3
        }
        if (shedStat == 3 && y == "Crowbar"){
            shedStat = 2
        }
        if (shedStat == 2 && y == "Key"){
            shedStat = 1
            // exit menu loop, game is won, Shed is unlocked
            continueMenuLoop = false
            println("You have done it! You opened the Shed and found a lot of materials inside. You take it all to the beach to put it together.")
            println("Congrats you have escaped the island!")

            menu()

        }else{
            shedError
        }

    }




    def locationError{
        println("Location not found... Please select an area")
        menu()
    }
    def locationNotAvailable{
        println(s"You are already at $location. Please select another location.")
        menu()
    }
    def shedError{
        println("Action unavailable...")
        menu()
    }
    def inputError{
        println("Invalid action. Try again.")
        menu()
    }
}
