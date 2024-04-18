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
  ArrayList<BookingSave> bookinglist =
      new ArrayList<>(); // Add a new arraylist with bookinginformation for task 2
  String Nextavaildate = ""; // the next available date for Task2.10,11

  public VenueHireSystem() {}

  public String TheNextavailabledate(String code) {
    this.Nextavaildate = currentdate;
    ArrayList<String> dates = new ArrayList<String>();
    for (BookingSave booking : bookinglist) {
      if (booking.BookingCode.equals(code)) {
        dates.add(booking.BookingDate);
        System.out.println(booking.BookingDate);
      }
    }

    while (dates.contains(Nextavaildate)) {
      this.Nextavaildate = increaseDate(Nextavaildate);
    }

    return Nextavaildate;
  }

  public String increaseDate(String date) { // Add one day more if the date is used
    String[] separatedate = date.split("/");
    int dateday = Integer.parseInt(separatedate[0]);
    dateday = dateday + 1;
    String datedaystr = String.format("%02d", dateday);
    String check = datedaystr + "/" + separatedate[1] + "/" + separatedate[2];
    return check;
  }

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
            venue.getvenuename(),
            venue.getvenueCode(),
            venue.getcapacity(),
            venue.gethirefee(),
            TheNextavailabledate(venue.getvenueCode())); // Add Nextavaildate
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
            venue.getvenuename(),
            venue.getvenueCode(),
            venue.getcapacity(),
            venue.gethirefee(),
            TheNextavailabledate(venue.getvenueCode())); // Add Nextavaildate
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

  // Task 2 start
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

    for (BookingSave booking :
        bookinglist) { // check that the booking for a specific date is already made
      if (booking.BookingCode.equals(options[0]) && booking.BookingDate.equals(options[1])) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
            booking.BookingName, booking.BookingDate);
        return;
      }
    }

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
    // The venue must be available on the specified date (?)
    else {
      String bookingVenueName = "";
      String bookingCapacity = ""; // venue capacity
      for (venuesave venue : venuelist) {
        if (venue.getvenueCode().equals(options[0])) {
          bookingVenueName = venue.getvenuename();
          bookingCapacity = venue.getcapacity();
        }
      }
      int bookingCapacitynum = Integer.parseInt(bookingCapacity); // convert capacity to integer
      int currentcapacity = Integer.parseInt(options[3]); // convert given date to int
      int twentyfivecheck = bookingCapacitynum / 4; // 25 percentage of venue capacity
      String editedcapacity = options[3];
      if (currentcapacity
          < twentyfivecheck) { // if added capacity is less than 25% of venue capacity
        editedcapacity = "" + twentyfivecheck; // convert twentivyfivecheck to string
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            options[3], editedcapacity, bookingCapacity);
      } else if (currentcapacity > bookingCapacitynum) {
        editedcapacity = bookingCapacity;
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            options[3], editedcapacity, bookingCapacity);
      }
      String Bookingreference = BookingReferenceGenerator.generateBookingReference();
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          Bookingreference,
          bookingVenueName,
          options[1],
          editedcapacity); // Successfully booking message
      // change options[3] to edited capacity
      bookinglist.add(
          new BookingSave(
              Bookingreference, // Add bookingreference information too
              bookingVenueName,
              options[0],
              options[1],
              options[2],
              editedcapacity)); // Add new Booking information
      // change options[3] to edited capacity
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
    if (!venuecodelist.contains(venueCode)) { // If venue code doesn't exist
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // If venue code exist
    String venueName = "";
    for (venuesave venue : venuelist) {
      if (venue.getvenueCode().equals(venueCode)) {
        venueName = venue.getvenuename();
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName); // Print the Header
        break;
      }
    }

    // If venue code does exist in the bookinglist
    boolean exist = false;
    for (BookingSave booking : bookinglist) {
      if (booking.BookingCode.equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
            booking.getBookingReference(), booking.getBookingDate());
        exist = true;
      }
    }

    // If venue code does not exist in the bookinglist (when boolean exist is false)
    if (!exist) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
    }
  }

  // Task 3 start
  String cateringName = "0"; // Create a variable that saves the name of Catering Service
  String cateringTotal =
      "0"; // Create a variable that saves the TOTAL price of the catering service
  String musicTotal = "0"; // Create a variable that saves the price of the music service
  String FloralTotal = "0"; // Create a variable that saves the price of the floral service
  String FloralName = "0"; // Create a variable that saves the name(type) of the floral service

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
    if (bookinglist
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage(
          "Catering", bookingReference); // Error message
      return;
    }
    for (BookingSave booking : bookinglist) { // if there is no bookingReference in the booklist
      if (!booking.BookingReference.equals(bookingReference)) {
        MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage(
            "Catering", bookingReference); // Error message
        return;
      }
    }

    // Because enum shows only the cost of the Catering service per person, we need to find and save
    // the total.
    int bookingCapacity = 0; // find the booking capacity for the given reference
    for (BookingSave booking : bookinglist) {
      if (booking.BookingReference.equals(bookingReference)) {
        bookingCapacity =
            Integer.parseInt(booking.getBookingCapacity()); // Save the value as an integer
      }
    }
    int cateringTotalint =
        cateringType.getCostPerPerson()
            * bookingCapacity; // Total cost of catering = cost of catering per person * number of
    // person
    this.cateringTotal = "" + cateringTotalint;

    // Catering service SUCCESSFUL message
    this.cateringName = cateringType.getName();
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringName + ")", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
    if (bookinglist
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
      return;
    }
    for (BookingSave booking : bookinglist) { // if there is no bookingReference in the booklist
      if (!booking.BookingReference.equals(bookingReference)) {
        MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
        return;
      }
    }

    // Because the music service is only one, save the cost of the music service only and print out
    // the success message.
    this.musicTotal = "500";
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
    if (bookinglist
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
      return;
    }
    for (BookingSave booking : bookinglist) { // if there is no bookingReference in the booklist
      if (!booking.BookingReference.equals(bookingReference)) {
        MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
        return;
      }
    }

    // Total cost of floral
    int FloralTotalint = floralType.getCost();
    this.FloralTotal = "" + FloralTotalint;
    // Print out success message
    this.FloralName = floralType.getName();
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral (" + FloralName + ")", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    if (bookinglist
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }
    for (BookingSave booking : bookinglist) { // if there is no bookingReference in the booklist
      if (!booking.BookingReference.equals(bookingReference)) {
        MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
        return;
      }
    }

    // Invoice start
    // Find values to use in the invoice by using bookingsave and venuesave
    String bookingMail = "";
    String bookingDate = "";
    String bookingCode = "";
    String bookingCapacity = "";
    for (BookingSave booking : bookinglist) {
      if (booking.BookingReference.equals(bookingReference)) {
        bookingMail = booking.getBookingMail();
        bookingDate = booking.getBookingDate();
        bookingCode = booking.getBookingCode();
        bookingCapacity = booking.getBookingCapacity();
      }
    }
    String venueFee = "";
    String venueName = "";
    for (venuesave a : venuelist) {
      if (a.venueCode.equals(bookingCode)) {
        venueFee = a.gethirefee();
        venueName = a.getvenuename();
      }
    }
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference,
        bookingMail,
        currentdate,
        bookingDate,
        bookingCapacity,
        venueName); // Top Invoice

    // Invoice. cost from each services and venue fee. Based on these values, the total cost will be
    // calculated
    if (venueFee
        != "0") { // venueFee = 0 means that the venue has not not been used, so we don't have to
      // print the message.
      MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(venueFee);
    }
    if (cateringTotal
        != "0") { // if cateringTotal = 0, the catering service has not been used. Therefore, we
      // don't have to print the message.
      MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(cateringName, cateringTotal);
    }
    if (musicTotal != "0") { // Same function as cateringTotal
      MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(musicTotal);
    }
    if (FloralTotal != "0") { // Same function as cateringTotal
      MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(FloralName, FloralTotal);
    }

    String totalAmount =
        CountTotal(venueFee, cateringTotal, musicTotal, FloralTotal); // calculate the total cost
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(totalAmount); // Bottom Invoice Message
  }

  public String CountTotal(String venue, String catering, String music, String floral) {
    int venuenum = Integer.parseInt(venue); // Convert String values to Integers for calculation
    int cateringnum = Integer.parseInt(catering);
    int musicnum = Integer.parseInt(music);
    int floralnum = Integer.parseInt(floral);

    int totalnum =
        venuenum
            + cateringnum
            + musicnum
            + floralnum; // Total sum is the sum of all the integer values
    String total =
        ""
            + totalnum; // Integer again converts to String to return String values that can be use
                        // in the MessageCli.

    return total;
  }
}
