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
      // Precisamos registrar o driver do MySQL
      // Segundo https://www.baeldung.com/java-jdbc-loading-drivers, JDBC <= 4 e Java SE <= 1.6 nao carregava automaticamente
      // As versoes atuais do JDBC nao precisa mais
      // Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();

      // Criamos uma instancia de Connection que vai usar o protocolo do MySQL
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledb?useCursorFetch=true", "root", "example");

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
      rs.setFetchSize(1);

      System.out.println("Fetch size: " + rs.getFetchSize());

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
