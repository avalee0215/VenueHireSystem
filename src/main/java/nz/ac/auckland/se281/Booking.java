package nz.ac.auckland.se281;

public class Booking {
  String BookingReference; // not used yet
  String BookingCode;
  String BookingDate;
  String BookingMail;
  String BookingCapacity;
  String BookingName;

  public Booking(
      String reference, String name, String code, String date, String mail, String capacity) {
    this.BookingCode = code;
    this.BookingDate = date;
    this.BookingMail = mail;
    this.BookingCapacity = capacity;
    this.BookingName = name;
    this.BookingReference = reference; // save reference for print booking
  }

  public String getBookingCode() {
    return BookingCode;
  }

  public String getBookingName() {
    return BookingName;
  }

  public String getBookingDate() {
    return BookingDate;
  }

  public String getBookingMail() {
    return BookingMail;
  }

  public String getBookingCapacity() {
    return BookingCapacity;
  }

  public String getBookingReference() {
    return BookingReference;
  }
}
