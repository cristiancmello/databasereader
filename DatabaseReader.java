import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseReader {
  private Connection conn;
  private Statement stmt;
  private ResultSet rs;

  public void open() {
    try {
      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exampledb", "postgres", "example");

      System.out.println("Connection created.");
    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
  }

  public void fetchAll() {
    try {
      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      rs = stmt.executeQuery("SELECT * FROM persons");

    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }
  }

  public int howManyRecords() {
    Statement stmt;
    ResultSet rs;
    
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT COUNT(*) as SIZE FROM persons");

      rs.next();

      int size = rs.getInt("SIZE");

      return size;
    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }

    return -1;
  }

  public void close() {
    try {
      conn.close();

      System.out.println("Connection was closed.");
    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }
  }

  public void goToFirst() {
    try {
      rs.first();
      
    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }
  }

  public void positionRecord(int key) {
    try {
      rs.absolute(key);

    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }
  }

  public void goToLast() {
    try {
      rs.last();
      
    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }
  }

  public String getRecord(int key) {
    try {
      positionRecord(key);
      
      return "{%d, %s}".formatted(rs.getInt("person_id"), rs.getString("name"));
    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }

    return "{}";
  }

  public String currentRecord() {
    try {
      return "{%d, %s}".formatted(rs.getInt("person_id"), rs.getString("name"));
    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }
    
    return "{}";
  }

  public String getNextRecord() {
    try {
      rs.next();

      return "{%d, %s}".formatted(rs.getInt("person_id"), rs.getString("name"));
    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }

    return "{}";
  }

  public boolean areThereMoreRecords() {
    try {
      return !rs.isLast();
      
    } catch (SQLException ex) {
      System.err.println("SQLException: " + ex.getMessage());
      System.err.println("SQLState: " + ex.getSQLState());
      System.err.println("VendorError: " + ex.getErrorCode());
    }

    return false;
  }
}
