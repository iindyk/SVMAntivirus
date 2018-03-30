import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {
    private final static Logger LOGGER = Logger.getLogger(SQLiteConnection.class.getName());
    private Connection conn;
    private static SQLiteConnection instance = null;

    private SQLiteConnection(){
        conn = null;
        LOGGER.addHandler(WindowHandler.getInstance());
        try {
            // db parameters
            String url = "jdbc:sqlite:/home/iindyk/IdeaProjects/SVMAntivirus/db.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            LOGGER.log(Level.INFO, "Connection to SQLite has been established.");

        } catch (SQLException e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
    }

    public static SQLiteConnection getInstance(){
        if (instance==null) instance = new SQLiteConnection();
        return instance;
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
                LOGGER.log( Level.INFO,"Connection to SQLite has been closed.");
            }
        }
        catch (SQLException e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
    }

    public ResTuple exec(String s){
        Statement statement=null;
        ResultSet resultSet=null;

        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(s);
        }
        catch (SQLException e){
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        return new ResTuple(statement, resultSet);
    }

    public class ResTuple{
        Statement statement;
        ResultSet resultSet;

        ResTuple(Statement statement, ResultSet resultSet) {
            this.statement = statement;
            this.resultSet = resultSet;
        }
        public void close(){
            try{
                statement.close();
                resultSet.close();
            }
            catch (SQLException e){
                LOGGER.log( Level.SEVERE, e.toString(), e);
            }

        }
    }

    public static void main(String[] args) {

    }
}
