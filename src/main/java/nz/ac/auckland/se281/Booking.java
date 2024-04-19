package nz.ac.auckland.se281;

public class Booking { // Booking class to store booking information
  private String bookingReference;
  private String bookingCode;
  private String bookingDate;
  private String bookingMail;
  private String bookingCapacity;
  private String bookingName;

  public Booking(
      String reference,
      String name,
      String code,
      String date,
      String mail,
      String capacity) { // constructor
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
