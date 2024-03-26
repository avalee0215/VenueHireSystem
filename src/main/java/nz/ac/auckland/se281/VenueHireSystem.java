package nz.ac.auckland.se281;


import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  

  public VenueHireSystem() {

  }

  public void printVenues() {
    // TODO implement this method
    MessageCli.NO_VENUES.printMessage(); // Use MessageCli to print
    }
    
    
  

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    String venueempty = venueName.trim(); // erase all the space in the venueCode string to check it is empty or not

    if (venueempty.isEmpty()) // if the trimmed string is empty, print the message
    {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    }
    else
  {
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName,venueCode); // Print out Successfully created venue by using MessageCli
  }
    
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
