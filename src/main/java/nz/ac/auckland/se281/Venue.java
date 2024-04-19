package nz.ac.auckland.se281;

public class Venue {
  // Venue class to store venue information
  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hireFeeInput;

  public Venue(String venueName, String venueCode, String capacityInput, String hirefeeInput) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacityInput = capacityInput;
    this.hireFeeInput = hirefeeInput;
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getCapacity() {
    return capacityInput;
  }

  public String getHireFee() {
    return hireFeeInput;
  }
}
