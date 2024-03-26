package nz.ac.auckland.se281;

public class venueSave {
  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hireFeeInput;

  public venueSave(String venueName, String venueCode, String capacityInput, String hirefeeInput) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacityInput = capacityInput;
    this.hireFeeInput = hirefeeInput;
  }

  public String getvenuename() {
    return venueName;
  }

  public String getvenueCode() {
    return venueCode;
  }

  public String getcapacity() {
    return capacityInput;
  }

  public String gethirefee() {
    return hireFeeInput;
  }
}
