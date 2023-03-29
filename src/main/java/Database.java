import java.sql.*;

public class Database {

    public Connection DatabaseConnect() {
        String url = "jdbc:mysql://localhost:3306/morpion";
        String user = "bsd";
        String password = "bsd2";
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    
    public ChangeScore(String pseudo, int won) {
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO scores (player_name, score, ) VALUES (" + $pseudo + "," +  $won")";
        stmt.executeUpdate(sql);
    }
}
