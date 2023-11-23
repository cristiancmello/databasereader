
public class Application {
  public static void main(String[] args) {
    DatabaseReader databaseReader = new DatabaseReader();
    databaseReader.open();

    databaseReader.fetchAll();

    System.out.println("Total Records: " + databaseReader.howManyRecords());

    System.out.println("First Record: " + databaseReader.getNextRecord());
    System.out.println("Second Record: " + databaseReader.getNextRecord());

    databaseReader.goToFirst();
    System.out.println("First record: " + databaseReader.currentRecord());

    databaseReader.goToLast();
    System.out.println("Last record: " + databaseReader.currentRecord());

    databaseReader.positionRecord(2);
    System.out.println("Second record: " + databaseReader.currentRecord());

    databaseReader.goToLast();

    System.out.println("Are there more records? " + databaseReader.areThereMoreRecords());

    System.out.println("Third record: " + databaseReader.getRecord(3));

    databaseReader.close();
  }
}
