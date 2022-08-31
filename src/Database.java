import java.sql.*;

public class Database {
    //This method executes a query and returns the number of albums for the artist with name artistName

    //select count(album.artistid) from artist inner join album on artist.artistid=album.artistid where artist.name='david bowie';

    private static Connection connection;

    public int getTitles(String artistName) {
        int titleNum = 0;
        //Implement this method
        //Prevent SQL injections!

        try {
            PreparedStatement stmt=connection.prepareStatement("select count(album.artistid) from artist inner join album on artist.artistid=album.artistid where artist.name= ?");


            stmt.setString(1,artistName);
            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                titleNum=rs.getInt("count");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


        return titleNum;
    }

    // This method establishes a DB connection & returns a boolean status
    public boolean establishDBConnection() {
        //Implement this method
        //5 sec timeout

        try {

            Class.forName("org.postgresql.Driver");


            connection = DriverManager.getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);
            return connection.isValid(5);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}