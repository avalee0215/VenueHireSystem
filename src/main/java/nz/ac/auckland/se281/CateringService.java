package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service {
  private int
      costPerPerson; // as the catering service provides the cost per person only, new variable is
  // needed
  private int
      venueCapacity; // find the venue capacity of the given reference number. It won't be change

  public CateringService(CateringType cateringType) {
    super(); // bring the values from the Service class, so I can save the name of the service and
    // the total cost
    this.name = cateringType.getName();
    this.costPerPerson = cateringType.getCostPerPerson();
  }

  @Override
  public void totalCostService(String reference, Booking booking) {
    venueCapacity = Integer.parseInt(booking.getBookingCapacity());
    this.totalCost =
        String.valueOf(
            costPerPerson
                * venueCapacity); // total cost of the catering service is the capacity * cost per
    // person
  }
}
