package DataBase;
import java.sql.*;

public class dataBaseFile {
    Connection c;
    Statement s;
    ResultSet r;
    public dataBaseFile() {
        //grazie xirgtui per la sub
    }

    public void openDatabase() {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:saves.db");
            s = c.createStatement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int loadDataBaseLevel(String user) {
        try {
            r = s.executeQuery("select * from " + user);
            int level = r.getInt("level");
            return level;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String loadDataBaseName(String user) {
        try {
            r = s.executeQuery("select * from " + user);
            String name = r.getString("name");
            return name;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void storeDataBase(int level, String name, String user) {
        try {
            int cosa = s.executeUpdate("update " + user + " set name = '" + name + "'");
            int cosa2 = s.executeUpdate("update "+ user +" set level = " + level);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeDataBase() {
        try {
            s.close();
            c.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
