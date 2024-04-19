package nz.ac.auckland.se281;

public class Venue {
  String venueName;
  String venueCode;
  String capacityInput;
  String hireFeeInput;

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
