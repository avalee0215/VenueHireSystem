package nz.ac.auckland.se281;

public class BookingSave {
  String BookingReference; // not used yet
  String BookingCode;
  String BookingDate;
  String BookingMail;
  String BookingCapacity;
  String BookingName;

  public BookingSave(String name, String code, String date, String mail, String capacity) {
    this.BookingCode = code;
    this.BookingDate = date;
    this.BookingMail = mail;
    this.BookingCapacity = capacity;
    this.BookingName = name;
  }
}
