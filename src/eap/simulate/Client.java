package eap.simulate;

import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.Phone;
import eap.abstractfactory.SmartPhone;
//Import Camera Library
import eap.abstractfactory.Camera;

import eap.observer.PhoneCreationListener;
import eap.observer.PhoneOrderHandler;
import eap.simulate.Simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements PhoneCreationListener {

    private final String name;
    private final Class interestedFor;
    private Phone phone;
    /*Για τους παρόχους κινητής τηλεφωνίας έχουμε τις εξής παραδοχές:
    1. Είναι οι 3 που υπάρχουν στην ακόλουθη λίστα
    2. Η Cosmote έχει κινητά που ξεκινάνε 697, 698 και 699, η Vodafone 694, 695 και 696 και η Wind 691,692 και 693.
    3. Κινητά που ξεκινάνε ως 690 θα αναφέρονται ως "Διαφημιστικά".*/
    private static final String[] carriers = {"Cosmote", "Vodafone", "Wind"};

    public Phone getPhone() {
        return phone;
    }

    private void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Client(String name, Class interestedFor) {
        this.name = name;
        this.interestedFor = interestedFor;
        PhoneOrderHandler.addListener(this);
    }

    public String getName() {
        return name;
    }

    public Class getInterestedFor() {
        return interestedFor;
    }

    @Override
    public void update(Phone phone) {
//      Find the phone that wants
        String clientPhoneWants = this.getInterestedFor().getSimpleName();
        if (clientPhoneWants.equals(phone.getClass().getSimpleName())) {
            if (PhoneOrderHandler.phoneExistsInList(phone)) {
//            Remove phone
                PhoneOrderHandler.removePhone(phone);
//            Add phone to client
                this.setPhone(phone);
//            Remove Client From simularion arraylist
                Simulation.waitingClients.remove(name);
                PhoneOrderHandler.removeListener(this);

//                Print Phone to Console
                System.out.println("\nHi, I am " + name + "(" + clientPhoneWants + ") and I got my new phone!");
                System.out.println("Phone specs:");
                System.out.println(phone.toString());
                System.out.println("Phone number valid:" + checkNumberValidity(phone.getPhoneNumber()));
                System.out.println("Phone number carrier:" + getCarrierName(phone.getPhoneNumber()));
//                Taka picture or do a call 
                usePhone(phone);
            }
        }
    }

    /*Η μέθοδος χρήσης του κινητού τηλεφώνου θα κάνει τα εξής:
    Στην περίπτωση FeaturePhone θα καλεί τον αριθμό +30123456789
    Στην περίπτωση SmartPhone Θα βγάζει μια φωτογραφία με φλας και ανάλυση 12 MP*/
    private void usePhone(Phone phone) {
        if (phone.getClass().getSimpleName().equals("SmartPhone")) {
//            SmartPhone taking picture
            Camera camera = new Camera(12);
            camera.setSelectedResolution(12);
            camera.setUseFlash(true);
            camera.takePicture();
        } else {
//            FeaturePhone do a call 
            phone.callNumber("+30123456789");
        }
    }

    //Σύμφωνα με την περιγραφή πάνω από τον πίνακα carriers, η μέθοδος επιστρέφει είτε το όνομα του carrier, είτε Διαφημιστικά
    public String getCarrierName(String phoneNumber) {
//      Check with the 3 digits of the phone 
        int tempCarriers = Integer.parseInt(phoneNumber.substring(3, 6));
//        Check if the number is Wind
        if (tempCarriers == 691 || tempCarriers == 692 || tempCarriers == 693) {
            return carriers[2];
//        Check if the number is Cosmote
        } else if (tempCarriers == 694 || tempCarriers == 695 || tempCarriers == 696) {
            return carriers[1];
//        Check if the number is Vodafone
        } else if (tempCarriers == 697 || tempCarriers == 698 || tempCarriers == 699) {
            return carriers[0];
        }
//        Else "Διαφημιστικά"
        return "Διαφημιστικά";
    }

    /*Για να είναι έγκυρο ένα κινητό τηλέφωνο πρέπει να ισχύουν ταυτόχρονα τα εξής:
    1. Να ξεκινάει από +3069 (είναι όλα από Ελλάδα και είναι όλα κινητά)
    2. Στη συνέχεια να υπάρχουν ακριβώς 10 αριθμοί
    3. Παραδοχή ότι δεν υπάρχουν κενά (white spaces μεταξύ των αριθμών)*/
    public boolean checkNumberValidity(String phoneNumber) {
//      Declare a greece number
        String greeceNumber = "+3069";
//        Check the number if starts with greeceNumber variable
        if (phoneNumber.substring(0, 5).equals(greeceNumber)) {
//            Check if the phone number has 5+8=13 characters
            if (phoneNumber.length() == 13) {
//                Check the phone if has empty spaces
                if (!phoneNumber.contains(" ")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
