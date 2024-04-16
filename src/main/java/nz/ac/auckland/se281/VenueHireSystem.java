package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  ArrayList<venuesave> venuelist =
      new ArrayList<>(); // New Arraylist to save venue's information. Change <String> to
  // <venuesave> so we can use the constructor
  ArrayList<String> venuecodelist =
      new ArrayList<String>(); // Add a new arraylist with venuecodes only for task 2

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method

    ArrayList<String> venuenumber = new ArrayList<>(); // Create the arraylist that save one to nine
    venuenumber.add("one");
    venuenumber.add("two");
    venuenumber.add("three");
    venuenumber.add("four");
    venuenumber.add("five");
    venuenumber.add("six");
    venuenumber.add("seven");
    venuenumber.add("eight");
    venuenumber.add("nine");

    if (venuelist.size() == 0) // when there is no venue, // change i to venuelist.size
    {
      MessageCli.NO_VENUES.printMessage(); // Use MessageCli to print
    } else if (0 < venuelist.size() && venuelist.size() < 10) { // change i to venuelist.size
      if (venuelist.size() == 1) // change i to venuelist.size
      {
        MessageCli.NUMBER_VENUES.printMessage(
            "is",
            venuenumber.get(0),
            ""); // print the message stating how many venues are there. In this case, one.

      } else {
        int venuelistsize = venuelist.size() - 1;
        MessageCli.NUMBER_VENUES.printMessage(
            "are",
            venuenumber.get(venuelistsize),
            "s"); // print the message stating how many venues are there
      }
      for (int j = 0; j < venuelist.size(); j++) {
        venuesave venue = venuelist.get(j); // change code to use modified venuelist.
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getvenuename(), venue.getvenueCode(), venue.getcapacity(), venue.gethirefee());
      }
    } else // if created venues are 10 or more than 10
    {
      int venuelistsize = venuelist.size();
      MessageCli.NUMBER_VENUES.printMessage(
          "are",
          String.valueOf(venuelistsize),
          "s"); // print the message stating how many venues are there
      for (int j = 0; j < venuelist.size(); j++) {
        venuesave venue = venuelist.get(j);
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getvenuename(), venue.getvenueCode(), venue.getcapacity(), venue.gethirefee());
      }
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    boolean hireFeeinteger =
        false; // check that the hireFeeinput is integer or not initially (create it to use in the
    // if
    // else statement)

    try {
      int hirefeetonumber = Integer.parseInt(hireFeeInput);
      if (hirefeetonumber > 0) { // Check if hire fee is a positive integer
        hireFeeinteger = true;
      } else {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    boolean capacityinteger = false;
    try {
      int capacitytonumber = Integer.parseInt(capacityInput);
      if (capacitytonumber > 0) { // Check if hire fee is a positive integer
        capacityinteger = true;
      } else {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    boolean repeatedvenueCode = false;
    int repeatedvenuenumber = 0;

    // Check if the venue code is already used
    for (venuesave item : venuelist) {
      if (item.getvenueCode().equals(venueCode)) {
        repeatedvenueCode = true;
        repeatedvenuenumber = venuelist.indexOf(item);
        break;
      }
    }

    if (venueName.trim().isEmpty()) // if the trimmed string is empty, print the message
    {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (repeatedvenueCode) // If the code already used,
    {
      String usedvenue =
          venuelist
              .get(repeatedvenuenumber)
              .getvenuename(); // to find out the name of the venue that already used the code
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, usedvenue);
    } else {

      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(
          venueName, venueCode); // Print out Successfully created venue by using MessageCli
      venuelist.add(
          new venuesave(
              venueName,
              venueCode,
              capacityInput,
              hireFeeInput)); // add venue's information in the venuelist
      venuecodelist.add(venueCode); // add venue code separately to make a booking
    }
  }

  // Use object&instances method to save the currentdate
  String currentdate = null;

  public void setSystemDate(String dateInput) {
    // TODO implement this method
    // Set the system's date to the specificed data and prints a confirmation message
    this.currentdate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    // TODO implement this method
    // prints the system's current date
    if (currentdate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(currentdate);
    }
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
    // the system's date must be set

    if (currentdate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    } else if (venuelist.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES
          .printMessage(); // There must be at least one venue in the system
    } else if (!venuecodelist.contains(options[0])) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(
          options[0]); // The venue code must exist,
    } else if (!compareDate(
        currentdate,
        options[
            1])) { // The booking date must not be in the past (today or later is OK in terms of the
                   // current system date).
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1]);
    }
  }

  private static boolean compareDate(String current, String compare) {
    String[] separatecurrent = current.split("/"); // separate both strings by day, month, and year
    String[] separatecompare = compare.split("/");
    for (int i = 2; i >= 0; i--) {
      int currentint = Integer.parseInt(separatecurrent[i]); // compare year - month - date order
      int compareint = Integer.parseInt(separatecompare[i]);
      if (currentint
          > compareint) { // if the current date is larger than booking date, it means the booking
                          // is unavailable.
        return false; // return false in this case and break the class
      }
    }
    return true;
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
