package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  ArrayList<Venue> venueList =
      new ArrayList<>(); // New Arraylist to save venue's information. Change <String> to
  // <venuesave> so we can use the constructor
  ArrayList<String> venueCodeList =
      new ArrayList<String>(); // Add a new arraylist with venuecodes only for task 2
  ArrayList<Booking> bookingList =
      new ArrayList<>(); // Add a new arraylist with bookinginformation for task 2
  String nextAvailableDate = ""; // the next available date for Task2.10,11

  public VenueHireSystem() {}

  public String findNextAvailableDate(String code) {
    if (systemDate == null) {
      return "%s";
    }
    this.nextAvailableDate = systemDate;
    ArrayList<String> dates = new ArrayList<String>();
    for (Booking booking : bookingList) {
      if (booking.BookingCode.equals(code)) {
        dates.add(booking.BookingDate);
        System.out.println(booking.BookingDate);
      }
    }

    while (dates.contains(nextAvailableDate)) {
      this.nextAvailableDate = increaseDate(nextAvailableDate);
    }

    return nextAvailableDate;
  }

  public String increaseDate(String date) { // Add one day more if the date is used
    String[] splitDate = date.split("/");
    int day = Integer.parseInt(splitDate[0]);
    day = day + 1;
    String formattedDay = String.format("%02d", day);
    String formattedDate = formattedDay + "/" + splitDate[1] + "/" + splitDate[2];
    return formattedDate;
  }

  public void printVenues() {

    ArrayList<String> venueNumber = new ArrayList<>(); // Create the arraylist that save one to nine
    venueNumber.add("one");
    venueNumber.add("two");
    venueNumber.add("three");
    venueNumber.add("four");
    venueNumber.add("five");
    venueNumber.add("six");
    venueNumber.add("seven");
    venueNumber.add("eight");
    venueNumber.add("nine");

    if (venueList.size() == 0) // when there is no venue, // change i to venuelist.size
    {
      MessageCli.NO_VENUES.printMessage(); // Use MessageCli to print
    } else if (0 < venueList.size() && venueList.size() < 10) { // change i to venuelist.size
      if (venueList.size() == 1) // change i to venuelist.size
      {
        MessageCli.NUMBER_VENUES.printMessage(
            "is",
            venueNumber.get(0),
            ""); // print the message stating how many venues are there. In this case, one.

      } else {
        int sizeOfVenueList = venueList.size() - 1;
        MessageCli.NUMBER_VENUES.printMessage(
            "are",
            venueNumber.get(sizeOfVenueList),
            "s"); // print the message stating how many venues are there
      }
      for (int j = 0; j < venueList.size(); j++) {
        Venue venue = venueList.get(j); // change code to use modified venuelist.
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getvenuename(),
            venue.getvenueCode(),
            venue.getcapacity(),
            venue.gethirefee(),
            findNextAvailableDate(venue.getvenueCode())); // Add Nextavaildate
      }
    } else // if created venues are 10 or more than 10
    {
      int venuelistsize = venueList.size();
      MessageCli.NUMBER_VENUES.printMessage(
          "are",
          String.valueOf(venuelistsize),
          "s"); // print the message stating how many venues are there
      for (int j = 0; j < venueList.size(); j++) {
        Venue venue = venueList.get(j);
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getvenuename(),
            venue.getvenueCode(),
            venue.getcapacity(),
            venue.gethirefee(),
            findNextAvailableDate(venue.getvenueCode())); // Add Nextavaildate
      }
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    try {
      int hireFeeAsNumber = Integer.parseInt(hireFeeInput);
      if (hireFeeAsNumber > 0) { // Check if hire fee is a positive integer

      } else {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    try {
      int capacityAsNumber = Integer.parseInt(capacityInput);
      if (capacityAsNumber > 0) { // Check if hire fee is a positive integer
      } else {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    boolean isVenueCodeRepeated = false;
    int countRepeatedVenue = 0;

    // Check if the venue code is already used
    for (Venue item : venueList) {
      if (item.getvenueCode().equals(venueCode)) {
        isVenueCodeRepeated = true;
        countRepeatedVenue = venueList.indexOf(item);
        break;
      }
    }

    if (venueName.trim().isEmpty()) // if the trimmed string is empty, print the message
    {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (isVenueCodeRepeated) // If the code already used,
    {
      String usedVenue =
          venueList
              .get(countRepeatedVenue)
              .getvenuename(); // to find out the name of the venue that already used the code
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, usedVenue);
    } else {

      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(
          venueName, venueCode); // Print out Successfully created venue by using MessageCli
      venueList.add(
          new Venue(
              venueName,
              venueCode,
              capacityInput,
              hireFeeInput)); // add venue's information in the venuelist
      venueCodeList.add(venueCode); // add venue code separately to make a booking
    }
  }

  // Task 2 start
  // Use object&instances method to save the currentdate
  String systemDate = null;

  public void setSystemDate(String dateInput) {
    // Set the system's date to the specificed data and prints a confirmation message
    this.systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    // prints the system's current date
    if (systemDate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  public void makeBooking(String[] options) {
    // the system's date must be set

    for (Booking booking :
        bookingList) { // check that the booking for a specific date is already made
      if (booking.BookingCode.equals(options[0]) && booking.BookingDate.equals(options[1])) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
            booking.BookingName, booking.BookingDate);
        return;
      }
    }

    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    } else if (venueList.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES
          .printMessage(); // There must be at least one venue in the system
    } else if (!venueCodeList.contains(options[0])) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(
          options[0]); // The venue code must exist,
    } else if (!compareDate(
        systemDate,
        options[
            1])) { // The booking date must not be in the past (today or later is OK in terms of the
      // current system date).
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1]);
    }
    // The venue must be available on the specified date (?)
    else {
      String venueName = "";
      String venueCapacity = ""; // venue capacity
      for (Venue venue : venueList) {
        if (venue.getvenueCode().equals(options[0])) {
          venueName = venue.getvenuename();
          venueCapacity = venue.getcapacity();
        }
      }
      int capacityAsNumber = Integer.parseInt(venueCapacity); // convert capacity to integer
      int givenCapacityAsNumber = Integer.parseInt(options[3]); // convert given date to int
      int minimumCapacity = capacityAsNumber / 4; // 25 percentage of venue capacity
      String editedCapacity = options[3];
      if (givenCapacityAsNumber
          < minimumCapacity) { // if added capacity is less than 25% of venue capacity
        editedCapacity = "" + minimumCapacity; // convert twentivyfivecheck to string
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            options[3], editedCapacity, venueCapacity);
      } else if (givenCapacityAsNumber > capacityAsNumber) {
        editedCapacity = venueCapacity;
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            options[3], editedCapacity, venueCapacity);
      }
      String referenceCode = BookingReferenceGenerator.generateBookingReference();
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          referenceCode, venueName, options[1], editedCapacity); // Successfully booking message
      // change options[3] to edited capacity
      bookingList.add(
          new Booking(
              referenceCode, // Add bookingreference information too
              venueName,
              options[0],
              options[1],
              options[2],
              editedCapacity)); // Add new Booking information
      // change options[3] to edited capacity
    }
  }

  private static boolean compareDate(String current, String compare) {
    String[] splitSystemDate = current.split("/"); // separate both strings by day, month, and year
    String[] splitGivenDate = compare.split("/");
    for (int i = 2; i >= 0; i--) {
      int systemDateDay = Integer.parseInt(splitSystemDate[i]); // compare year - month - date order
      int givenDateDay = Integer.parseInt(splitGivenDate[i]);
      if (systemDateDay
          > givenDateDay) { // if the current date is larger than booking date, it means the booking
        // is unavailable.
        return false; // return false in this case and break the class
      }
    }
    return true;
  }

  public void printBookings(String venueCode) {
    if (!venueCodeList.contains(venueCode)) { // If venue code doesn't exist
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // If venue code exist
    String venueName = "";
    for (Venue venue : venueList) {
      if (venue.getvenueCode().equals(venueCode)) {
        venueName = venue.getvenuename();
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName); // Print the Header
        break;
      }
    }

    // If venue code does exist in the bookinglist
    boolean isVenueCodeExist = false;
    for (Booking booking : bookingList) {
      if (booking.BookingCode.equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
            booking.getBookingReference(), booking.getBookingDate());
        isVenueCodeExist = true;
      }
    }

    // If venue code does not exist in the bookinglist (when boolean exist is false)
    if (!isVenueCodeExist) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
    }
  }

  // Task 3 start
  String cateringName = "0"; // Create a variable that saves the name of Catering Service
  String cateringTotalCost =
      "0"; // Create a variable that saves the TOTAL price of the catering service
  String musicTotalCost = "0"; // Create a variable that saves the price of the music service
  String floralTotalCost = "0"; // Create a variable that saves the price of the floral service
  String floralName = "0"; // Create a variable that saves the name(type) of the floral service

  public void addCateringService(String bookingReference, CateringType cateringType) {
    if (bookingList
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage(
          "Catering", bookingReference); // Error message
      return;
    }
    for (Booking booking : bookingList) { // if there is no bookingReference in the booklist
      if (!booking.BookingReference.equals(bookingReference)) {
        MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage(
            "Catering", bookingReference); // Error message
        return;
      }
    }

    CateringService cateringService = new CateringService(cateringType);
    for (Booking booking1 : bookingList) {
      if (booking1.BookingReference.equals(bookingReference)) {
        cateringService.totalCostService(
            bookingReference, booking1); // find the total cost for the catering service
        cateringTotalCost =
            cateringService
                .getTotalCost(); // update the cateringTotal value as the total cost value
      }
    }

    cateringName = cateringService.getName();
    // Catering service SUCCESSFUL message
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringService.getName() + ")", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    if (bookingList
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
      return;
    }
    for (Booking booking : bookingList) { // if there is no bookingReference in the booklist
      if (!booking.BookingReference.equals(bookingReference)) {
        MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
        return;
      }
    }

    MusicService musicService = new MusicService();
    musicTotalCost = musicService.totalCost;

    // Successful message of music
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    if (bookingList
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
      return;
    }
    for (Booking booking : bookingList) { // if there is no bookingReference in the booklist
      if (!booking.BookingReference.equals(bookingReference)) {
        MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
        return;
      }
    }

    FloralService floralService = new FloralService(floralType);
    for (Booking booking1 : bookingList) {
      if (booking1.BookingReference.equals(bookingReference)) {
        floralService.totalCostService(bookingReference, booking1);
        floralTotalCost = floralService.getTotalCost();
      }
    }

    floralName = floralService.getName();
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Floral (" + floralService.name + ")", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    if (bookingList
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }
    for (Booking booking : bookingList) { // if there is no bookingReference in the booklist
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
    for (Booking booking : bookingList) {
      if (booking.BookingReference.equals(bookingReference)) {
        bookingMail = booking.getBookingMail();
        bookingDate = booking.getBookingDate();
        bookingCode = booking.getBookingCode();
        bookingCapacity = booking.getBookingCapacity();
      }
    }
    String venueFee = "";
    String venueName = "";
    for (Venue a : venueList) {
      if (a.venueCode.equals(bookingCode)) {
        venueFee = a.gethirefee();
        venueName = a.getvenuename();
      }
    }
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference,
        bookingMail,
        systemDate,
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
    if (cateringTotalCost
        != "0") { // if cateringTotal = 0, the catering service has not been used. Therefore, we
      // don't have to print the message.
      MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(cateringName, cateringTotalCost);
    }
    if (musicTotalCost != "0") { // Same function as cateringTotal
      MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(musicTotalCost);
    }
    if (floralTotalCost != "0") { // Same function as cateringTotal
      MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(floralName, floralTotalCost);
    }

    String totalAmount =
        countTotalCost(
            venueFee,
            cateringTotalCost,
            musicTotalCost,
            floralTotalCost); // calculate the total cost
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(totalAmount); // Bottom Invoice Message
  }

  public String countTotalCost(String venue, String catering, String music, String floral) {
    int venueFeeAsNumber =
        Integer.parseInt(venue); // Convert String values to Integers for calculation
    int cateringFeeAsNumbe = Integer.parseInt(catering);
    int musicFeeAsNumber = Integer.parseInt(music);
    int floralFeeAsNumber = Integer.parseInt(floral);

    int totalCostAsNumber =
        venueFeeAsNumber
            + cateringFeeAsNumbe
            + musicFeeAsNumber
            + floralFeeAsNumber; // Total sum is the sum of all the integer values
    String totalCostAsString =
        ""
            + totalCostAsNumber; // Integer again converts to String to return String values that
                                 // can be use
    // in the MessageCli.

    return totalCostAsString;
  }
}
