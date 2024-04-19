package nz.ac.auckland.se281;

public class MusicService extends Service {
  private String cost =
      "500"; // Because Music has only one, change the total cost to 500. Otherwise, it is 0.

  public MusicService() {
    this.totalCost = "" + cost;
  }

  @Override
  public void totalCostService(String bookingReference, BookingSave booking) {}
}
