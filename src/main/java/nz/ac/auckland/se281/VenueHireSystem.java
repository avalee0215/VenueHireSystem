package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venueList =
      new ArrayList<>(); // New Arraylist to save venue's information.

  private ArrayList<String> venueCodeList =
      new ArrayList<String>(); // Add a new arraylist with venuecodes only
  private ArrayList<Booking> bookingList =
      new ArrayList<>(); // Add a new arraylist with bookinginformation
  private String nextAvailableDate = "";
  private String systemDate = null;

  private String cateringName = "0";
  private String cateringTotalCost = "0";
  private String musicTotalCost = "0";
  private String floralTotalCost = "0";
  private String floralName = "0";

  public VenueHireSystem() {}

  public String findNextAvailableDate(String code) {
    // Find the next available date for booking based on the booking
    if (systemDate == null) { // If the system date is not set, like Checkpoint 1, it will return %s
      return "%s";
    }
    this.nextAvailableDate = systemDate; // Initial next available date is the system date
    ArrayList<String> dates = new ArrayList<String>();
    for (Booking booking : bookingList) { // Check the bookinglist to find the next available date
      if (booking.getBookingCode().equals(code)) {
        dates.add(booking.getBookingDate());
        System.out.println(booking.getBookingDate());
      }
    }

    while (dates.contains(
        nextAvailableDate)) { // Check if the date is already used until the date is not used
      this.nextAvailableDate = increaseDate(nextAvailableDate);
    }

    return nextAvailableDate;
  }

  public String increaseDate(String date) { // Add one day more if the date is used
    String[] splitDate = date.split("/");
    int day = Integer.parseInt(splitDate[0]);
    day = day + 1;
    String formattedDay = String.format("%02d", day); // Format the day to two digits
    String formattedDate =
        formattedDay
            + "/"
            + splitDate[1]
            + "/"
            + splitDate[2]; // Format the date by adding the day, month, and year
    return formattedDate;
  }

  public void printVenues() { // Print out the venues that are created

    ArrayList<String> venueNumber =
        new ArrayList<>(); // Create the arraylist that save one to nine as a string
    venueNumber.add("one");
    venueNumber.add("two");
    venueNumber.add("three");
    venueNumber.add("four");
    venueNumber.add("five");
    venueNumber.add("six");
    venueNumber.add("seven");
    venueNumber.add("eight");
    venueNumber.add("nine");

    if (venueList.size() == 0) {
      MessageCli.NO_VENUES.printMessage(); // when there is no venue
    } else if (0 < venueList.size()
        && venueList.size() < 10) { // when there are venues less than 10
      if (venueList.size() == 1) {
        MessageCli.NUMBER_VENUES.printMessage(
            "is",
            venueNumber.get(0),
            ""); // print the message stating how many venues are there. In this case, one.
      } else {
        int sizeOfVenueList = venueList.size() - 1;
        MessageCli.NUMBER_VENUES.printMessage("are", venueNumber.get(sizeOfVenueList), "s");
      }
      for (int j = 0; j < venueList.size(); j++) {
        Venue venue = venueList.get(j); // change code to use modified venuelist.
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getCapacity(),
            venue.getHireFee(),
            findNextAvailableDate(venue.getVenueCode()));
      }
    } else { // if created venues are 10 or more than 10
      int venuelistsize = venueList.size();
      MessageCli.NUMBER_VENUES.printMessage(
          "are",
          String.valueOf(venuelistsize), // change i to venuelist.size(doesn't use the arraylist)
          "s"); // print the message stating how many venues are there
      for (int j = 0; j < venueList.size(); j++) {
        Venue venue = venueList.get(j);
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getCapacity(),
            venue.getHireFee(),
            findNextAvailableDate(
                venue.getVenueCode())); // Add Nextavaildate to the message after Task 2
      }
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // Create a venue by using the venue's name, code, capacity, and hire fee
    try { // Check if the capacity is positive integers. Otherwise, print the error message
      int hireFeeAsNumber = Integer.parseInt(hireFeeInput);
      if (hireFeeAsNumber > 0) {

      } else {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
    } catch (NumberFormatException e) { // If the hire fee is not a number, print the error message
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    try { // Check if the capacity is positive integers. Otherwise, print the error message
      int capacityAsNumber = Integer.parseInt(capacityInput);
      if (capacityAsNumber > 0) {
      } else {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }
    } catch (NumberFormatException e) { // If the capacity is not a number, print the error message
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    boolean isVenueCodeRepeated = false;
    int countRepeatedVenue = 0;
    for (Venue item : venueList) { // Check the venue list to find the repeated venue
      if (item.getVenueCode().equals(venueCode)) {
        isVenueCodeRepeated = true;
        countRepeatedVenue = venueList.indexOf(item);
        break;
      }
    }

    if (venueName.trim().isEmpty()) { // if the trimmed string is empty, print the message
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (isVenueCodeRepeated) { // If the code already used,
      String usedVenue =
          venueList
              .get(countRepeatedVenue)
              .getVenueName(); // to find out the name of the venue that already used the code
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, usedVenue);
    } else { // If the venue is successfully created, print the message
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
      venueList.add(new Venue(venueName, venueCode, capacityInput, hireFeeInput));
      venueCodeList.add(venueCode);
    }
  }

  // Task 2 start

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
    // Make a booking with the given options(values)

    for (Booking booking :
        bookingList) { // check that the booking for a venue on a given date does not already exist
      if (booking.getBookingCode().equals(options[0])
          && booking.getBookingDate().equals(options[1])) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
            booking.getBookingName(), booking.getBookingDate());
        return;
      }
    }

    if (systemDate == null) { // if the system date is not set, print the error message
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    } else if (venueList.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES
          .printMessage(); // if there is no venue in the system, print the error message
    } else if (!venueCodeList.contains(
        options[0])) { // if the venue code does not exist, print the error message
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
    } else if (!compareDate(systemDate, options[1])) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(
          options[
              1]); // if the booking date is in the past, print the error message. Works only when
                   // compareDate returns false
    } else { // if the booking is successful
      String venueName = "";
      String venueCapacity = "";
      // Find the venue name and capacity based on the venue code
      for (Venue venue : venueList) {
        if (venue.getVenueCode().equals(options[0])) {
          venueName = venue.getVenueName();
          venueCapacity = venue.getCapacity();
        }
        int capacityAsNumber = Integer.parseInt(venueCapacity);
        int givenCapacityAsNumber = Integer.parseInt(options[3]);
        int minimumCapacity =
            capacityAsNumber / 4; // 25 percentage of venue capacity, minimum capacity
        String editedCapacity = options[3];
        if (givenCapacityAsNumber < minimumCapacity) {
          editedCapacity =
              ""
                  + minimumCapacity; // if added capacity is less than 25% of venue capacity, change
                                     // the edited capacity to 25% of the venue capacity
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
              options[3], editedCapacity, venueCapacity);
        } else if (givenCapacityAsNumber
            > capacityAsNumber) { // if added capacity is more than the venue capacity more than
          // 100%, change the edited capacity to 100% of the venue capacity
          editedCapacity = venueCapacity;
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
              options[3], editedCapacity, venueCapacity);
        }
        String referenceCode = BookingReferenceGenerator.generateBookingReference();
        MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
            referenceCode, venueName, options[1], editedCapacity); // Successfully booking message
        bookingList.add(
            new Booking(
                referenceCode,
                venueName,
                options[0],
                options[1],
                options[2],
                editedCapacity)); // Add new Booking information to the bookinglist
      }
    }
  }

  public void printBookings(String venueCode) {
    if (!venueCodeList.contains(
        venueCode)) { // If venue code doesn't exist in the venuecodelist print the error message
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // If the venue code exist, find the venue name based on the venue code to print the header
    String venueName = "";
    for (Venue venue : venueList) {
      if (venue.getVenueCode().equals(venueCode)) {
        venueName = venue.getVenueName();
        MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName);
        break;
      }
    }

    // If venue code does exist in the bookinglist, print the message with the booking reference and
    // date
    boolean isVenueCodeExist = false;
    for (Booking booking : bookingList) {
      if (booking.getBookingCode().equals(venueCode)) {
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

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // Add a catering service to the booking with the given booking reference
    if (bookingList
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
      return;
    }
    for (Booking booking : bookingList) { // if there is no bookingReference in the booklist
      if (!booking.getBookingReference().equals(bookingReference)) {
        MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
        return;
      }
    }

    CateringService cateringService = new CateringService(cateringType);
    for (Booking booking1 :
        bookingList) { // find the booking reference in the bookinglist and update the total cost
      if (booking1.getBookingReference().equals(bookingReference)) {
        cateringService.totalCostService(
            bookingReference,
            booking1); // totalCostService will calculate the total cost of the catering service
        cateringTotalCost = cateringService.getTotalCost();
      }
    }

    cateringName = cateringService.getName();
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
      if (!booking.getBookingReference().equals(bookingReference)) {
        MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
        return;
      }
    }

    MusicService musicService = new MusicService();
    musicTotalCost =
        musicService.totalCost; // update the musicTotalCost value as the total cost value

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
      if (!booking.getBookingReference().equals(bookingReference)) {
        MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
        return;
      }
    }

    FloralService floralService = new FloralService(floralType);
    for (Booking booking1 :
        bookingList) { // find the booking reference in the bookinglist and update the total cost
      if (booking1.getBookingReference().equals(bookingReference)) {
        floralService.totalCostService(
            bookingReference,
            booking1); // totalCostService will calculate the total cost of the 'floral' service
        floralTotalCost = floralService.getTotalCost();
      }
    }

    floralName = floralService.getName();
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Floral (" + floralService.name + ")", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    // View the invoice for the booking with the given booking reference
    if (bookingList
        .isEmpty()) { // if there is no bookingReference(makeBooking has not been used yet)
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }
    for (Booking booking : bookingList) { // if there is no bookingReference in the booklist
      if (!booking.getBookingReference().equals(bookingReference)) {
        MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
        return;
      }
    }

    // Find the booking information based on the booking reference
    String bookingMail = "";
    String bookingDate = "";
    String bookingCode = "";
    String bookingCapacity = "";
    for (Booking booking : bookingList) {
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingMail = booking.getBookingMail();
        bookingDate = booking.getBookingDate();
        bookingCode = booking.getBookingCode();
        bookingCapacity = booking.getBookingCapacity();
      }
    }
    // Find the venue information based on the booking code
    String venueFee = "";
    String venueName = "";
    for (Venue a : venueList) {
      if (a.getVenueCode().equals(bookingCode)) {
        venueFee = a.getHireFee();
        venueName = a.getVenueName();
      }
    }
    // Invoice. Print the top half of the invoice with the finded information
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference,
        bookingMail,
        systemDate,
        bookingDate,
        bookingCapacity,
        venueName); // Top Invoice Message

    // Invoice. cost from each services and venue fee. Based on these values, the total cost will be
    // calculated
    if (venueFee != "0") { // venueFee = 0 means that the venue has not not been used
      MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(venueFee);
    }
    if (cateringTotalCost != "0") { // when the cateringTotalCost has not been used
      MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(cateringName, cateringTotalCost);
    }
    if (musicTotalCost != "0") {
      MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(musicTotalCost);
    }
    if (floralTotalCost != "0") {
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
    // countTotalCost will add up all the total costs(hire venue fee, catering, music, and floral
    // and return its value as a string)
    int venueFeeAsNumber = Integer.parseInt(venue);
    int cateringFeeAsNumbe = Integer.parseInt(catering);
    int musicFeeAsNumber = Integer.parseInt(music);
    int floralFeeAsNumber = Integer.parseInt(floral);

    int totalCostAsNumber =
        venueFeeAsNumber
            + cateringFeeAsNumbe
            + musicFeeAsNumber
            + floralFeeAsNumber; // Total sum is the sum of all the integer values
    String totalCostAsString = "" + totalCostAsNumber;

    return totalCostAsString;
  }

  private static boolean compareDate(String current, String compare) {
    // compareDate will compare the system date and the given date and return false if the given
    // date is the past
    String[] splitSystemDate = current.split("/"); // separate both strings by day, month, and year
    String[] splitGivenDate = compare.split("/");
    for (int i = 2; i >= 0; i--) {
      int systemDateDay = Integer.parseInt(splitSystemDate[i]);
      int givenDateDay = Integer.parseInt(splitGivenDate[i]);
      if (systemDateDay
          > givenDateDay) { // if the current date is larger than booking date, booking date is in
        // the past
        return false;
      }
    }
    return true;
  }
}
