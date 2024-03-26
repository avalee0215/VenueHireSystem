package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  ArrayList<venuesave> venuelist = new ArrayList<>(); //New Arraylist to save venue's information. Change <String> to <venuesave> so we can use the constructor
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

    if (venuelist.size()==0) // when there is no venue, // change i to venuelist.size
    { MessageCli.NO_VENUES.printMessage(); // Use MessageCli to print
    }
    else if (0<venuelist.size() && venuelist.size()<10){ // change i to venuelist.size
      if (venuelist.size()==1) // change i to venuelist.size
      {
        MessageCli.NUMBER_VENUES.printMessage("is", venuenumber.get(0), ""); //print the message stating how many venues are there. In this case, one.

      }
      else {
        int venuelistsize = venuelist.size()-1;
        MessageCli.NUMBER_VENUES.printMessage("are", venuenumber.get(venuelistsize), "s"); //print the message stating how many venues are there
      }
      for (int j = 0; j<venuelist.size(); j++){
        venuesave venue = venuelist.get(j); //change code to use modified venuelist.
        MessageCli.VENUE_ENTRY.printMessage(venue.getvenuename(),venue.getvenueCode(),venue.getcapacity(),venue.gethirefee());
      }
    }
    else // if created venues are 10 or more than 10
    {
      int venuelistsize = venuelist.size();
      MessageCli.NUMBER_VENUES.printMessage("are",String.valueOf(venuelistsize),"s"); //print the message stating how many venues are there
      for (int j = 0; j<venuelist.size(); j++){
        venuesave venue = venuelist.get(j);
        MessageCli.VENUE_ENTRY.printMessage(venue.getvenuename(),venue.getvenueCode(),venue.getcapacity(),venue.gethirefee());
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
    venuelist.add(new venuesave(venueName, venueCode, capacityInput, hireFeeInput)); // add venue's information in the venuelist
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
