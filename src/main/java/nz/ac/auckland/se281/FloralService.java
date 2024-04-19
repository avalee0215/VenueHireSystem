package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class FloralService extends Service {
  private int cost; // Integer cost that will soon change to String totalCost

  public FloralService(FloralType floralType) {
    super();
    this.name = floralType.getName();
    this.cost = floralType.getCost();
  }

  @Override
  public void totalCostService(
      String bookingReference,
      Booking booking) { // Compare to the Catering Service, the total cost is already given, so any
    // further calculation is not required
    this.totalCost = "" + cost; // Integer to String
  }
}
