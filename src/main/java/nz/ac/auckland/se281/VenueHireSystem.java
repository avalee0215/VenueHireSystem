package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  ArrayList<String> venuelist = new ArrayList<>(); //New Arraylist to save venue's information
  int i = 0; // integer that saves the number of venue. (How many venue has been made)
  public VenueHireSystem() {

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

    if (i==0) // when there is no venue,
    { MessageCli.NO_VENUES.printMessage(); // Use MessageCli to print
    }
    else if (0<i && i<10){
      if (i==1)
      {
        MessageCli.NUMBER_VENUES.printMessage("is", venuenumber.get(i-1), "");

      }
      else {
        MessageCli.NUMBER_VENUES.printMessage("are", venuenumber.get(i-1), "s");
      }
      for (int j = 0; j<venuelist.size(); ){
        MessageCli.VENUE_ENTRY.printMessage(venuelist.get(j), venuelist.get(j+1), venuelist.get(j+2), venuelist.get(j+3));
        j = j+4;
      }

    }
    
    
  }  
    
  

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    int capacitytonumber = Integer.parseInt(capacityInput);
    boolean hireFeeinteger = true;
    hireFeeinteger = hireFeeInput.matches("\\d+");

    

    if (venueName.trim().isEmpty()) // if the trimmed string is empty, print the message
    {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    }
    else if (capacitytonumber <= 0){
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity"," positive"); 
    }
    
    else if (hireFeeinteger != true){
      try{
        int hirefeetonumber = Integer.parseInt(hireFeeInput);
        if (hirefeetonumber <=0)
        {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee"," positive"); 
        }
      }
      catch(NumberFormatException e){
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      }
    }
    else
  {

    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName,venueCode); // Print out Successfully created venue by using MessageCli
    i++; //add the number of venue
    venuelist.add(venueName); //Add information in the venuelist
    venuelist.add(venueCode);
    venuelist.add(capacityInput);
    venuelist.add(hireFeeInput);
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
