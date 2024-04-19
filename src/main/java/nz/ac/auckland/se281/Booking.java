package nz.ac.auckland.se281;

public class Booking {
  String bookingReference; // not used yet
  String bookingCode;
  String bookingDate;
  String bookingMail;
  String bookingCapacity;
  String bookingName;

  public Booking(
      String reference, String name, String code, String date, String mail, String capacity) {
    this.bookingCode = code;
    this.bookingDate = date;
    this.bookingMail = mail;
    this.bookingCapacity = capacity;
    this.bookingName = name;
    this.bookingReference = reference; // save reference for print booking
  }

  public String getBookingCode() {
    return bookingCode;
  }

  public String getBookingName() {
    return bookingName;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getBookingMail() {
    return bookingMail;
  }

  public String getBookingCapacity() {
    return bookingCapacity;
  }

  public String getBookingReference() {
    return bookingReference;
  }
}
