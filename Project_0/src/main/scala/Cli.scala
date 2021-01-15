import scala.io.StdIn
import scala.util.matching.Regex 
import java.io.FileNotFoundException
import scala.io.Source
import scala.collection.mutable.ArrayBuffer


/*
* Need to make inventory a database instead of an Arraybuffer and the
* csv file will handle that database interaction???
* 
*/
class Cli{
    var location : String = "Beach"
    var inventory = new ArrayBuffer[String](4)
    var shedStat = 4
    

    val commandArg: Regex = "(\\w+)\\s*(.*)".r

    // print CSV File, has action words on it...
  //  val filename = "src/main/scala/gamestage_202101151036.csv"
  //  for(line <- Source.fromFile(filename).getLines){
  //      datab = line
  //  }

    def printOptions(): Unit ={
        println("Locations: ")
        println("Beach, Forest, Waterfall, Cliff, Shed")
        println("Actions: ")
        println("Look, Move, Use")
    }

    var continueMenuLoop = true
    def menu(): Unit = {
        while (continueMenuLoop) {
            printOptions
            printInv
            val input = StdIn.readLine()

            input match {
                case commandArg(cmd, arg) if cmd.equalsIgnoreCase("Look") => {
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
            if (inventory.contains("Bolt Cutters")){
                println("An overgrown forest. There is so much greenery here, you can't see the sky or ground.")
            }else{
                inventory += "Bolt Cutters" 
                println("You've found Bolt Cutters!")
            }
        }
        if (location.equalsIgnoreCase("shed")){
            if (inventory.contains("Net")){
                println("It is an old shed... I wonder who built it?")
            }else{
                inventory += "Net"
                println("You've found a Net!")
            }
        }
        if (location.equalsIgnoreCase("waterfall")){
            if (inventory.contains("Crowbar")){
                println("A not so big waterfall but a waterfall none of the less. It is quite peaceful here.")
            }else{
                inventory += "Crowbar"
                println("You've found a Crowbar!")
            }
        }
        if (location.equalsIgnoreCase("cliff")){
            if (inventory.contains("Key")){
                println("That's a long way down... Better keep my distance.")
            }
            if(inventory.contains("Net")){
                println("I bet my net could get that!")
            }else{
                println("There is a key dangling off the cliff! It is too far away to reach by hand...")
            }
        }
    }

    def moveArg(x: String) {
        //move to another area, can't move to an area you already are in
        //areas: Beach (start), Forest, Shed, Waterfall, Cliff
        if (location.equalsIgnoreCase(x)){
            locationNotAvailable
        }else{
            location = x
            println(s"You move to the $x.")
        }
    }

    def printInv{
        //prints inventory 
        println("Your Inventory:")
        println(inventory.toString())
    }

    def shedStatus(y: String){
        if (y.equalsIgnoreCase("Net")){
            inventory -= "Net"
            inventory += "Key"
            println("You've got a Key!")
        }
        if (shedStat == 4 && y.equalsIgnoreCase("Bolt Cutters")){
            shedStat = 3
            inventory -= "Bolt Cutters"
        }
        if (shedStat == 3 && y.equalsIgnoreCase("Crowbar")){
            shedStat = 2
            inventory -= "Crowbar"
        }
        if (shedStat == 2 && y.equalsIgnoreCase("Key")){
            shedStat = 1
            inventory -= "Key"
            // exit menu loop, game is won, Shed is unlocked
            continueMenuLoop = false
            println("You have done it! You opened the Shed and found a lot of materials inside. You take it all to the beach to put it together.")
            println("Congrats you have escaped the island!")

            menu()
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
