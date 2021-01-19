import java.sql.DriverManager
import java.sql.Connection


object  options{
    classOf[org.postgresql.Driver].newInstance()

    var conn: Connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/amburkee","amburkee","AshyBoy82")

    println("Actions:")
    val ac = conn.prepareStatement("SELECT * FROM actions;")
    ac.execute()
    val resultSet = ac.getResultSet()
    while (resultSet.next()){
        println(resultSet.getString("actions"))
    }


    println("Locations:")
    val loc = conn.prepareStatement("SELECT * FROM locations;")
    loc.execute()
    val resultSet2 = loc.getResultSet()
    while (resultSet2.next()){
        println(resultSet2.getString("locations"))
    }

}

