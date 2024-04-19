package nz.ac.auckland.se281;

public abstract class Service {
  // Service abstract class to store service information
  protected String
      name; // name is a type of the service. For example, when I use B in a catering service, the
  // name will be 'Breakfast'
  protected String totalCost; // total cost is the a cost of that specific type of the service.

  public Service() {
    this.totalCost = "0"; // Initially, totalCost is 0
  }

  public String getTotalCost() {
    return totalCost;
  }

  public String getName() {
    return name;
  }

  public abstract void totalCostService(String reference, Booking booking);
}
