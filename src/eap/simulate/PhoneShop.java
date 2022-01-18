package eap.simulate;

import eap.abstractfactory.Camera;
import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.Phone;
import eap.abstractfactory.SmartPhone;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneShop {

    //Ενδεικτική λίστα με εταιρείες κατασκευής κινητών τηλεφώνων. Θεωρούμε ότι κατασκευάζουν κινητά όλων των κατηγοριών
    private final String[] manufacturers = new String[]{"Samsung", "LG", "Apple", "Motorola"};
    //Ενδεικτική λίστα με ονόματα λειτουργικών συστημάτων κινητών
    private final String[] operatingSystems = new String[]{"Android", "iOS", "Tizen"};
    private final Random random = new Random();
    //Λίστα για την αποθήκευση των χαρακτηριστικών για κάθε τηλέφωνο που πρόκειται να παρχθεί
    private final List<Phone> phoneList = new ArrayList<>();

    public int numberOfPhones() {
        return phoneList.size();
    }

    //Όλα τα χαρακτηριστικά των τηλεφώνων πρέπει να παραχθούν με τυχαίο τρόπο
    public Phone createPhoneSpec() {
        Phone mobilePhone;

//        Random Phone
        int lowerBound;
        int upperBound = 2;
        int randomPhone = random.nextInt(upperBound);

//        Random Manufacturer
        upperBound = manufacturers.length;
        int randomNumber = random.nextInt(upperBound);
        String manufacturer = manufacturers[randomNumber];

//        Random Battery 
        lowerBound = 1000;
        upperBound = 5001;
        int batterySize = random.nextInt(upperBound - lowerBound) + lowerBound;

//      Random Screen 
        lowerBound = 500;
        upperBound = 3001;
        int randomWidth = random.nextInt(upperBound - lowerBound) + lowerBound;
        int randomHeight = random.nextInt(upperBound - lowerBound) + lowerBound;
        Dimension screenSize = new Dimension(randomWidth, randomHeight);

//      Random Phonenumber
        upperBound = 10;
        int intPhoneNumber;
//        Give greece number
        String phoneNumber = "+3069";
//        Loop for every digit to have the range 00000000-99999999
        for (int i = 0; i < 8; i++) {
            intPhoneNumber = random.nextInt(upperBound);
            phoneNumber += Integer.toString(intPhoneNumber);
        }

//      Random Storage
        lowerBound = 2;
        upperBound = 201;
        int storage = random.nextInt(upperBound - lowerBound) + lowerBound;

//      Random Camera 
        lowerBound = 10;
        upperBound = 101;
        int camera = random.nextInt(upperBound - lowerBound) + lowerBound;

//      0 = FeaturePhone and 1 = SmartPhone
        if (randomPhone == 0) {
            mobilePhone = new FeaturePhone(batterySize, screenSize, phoneNumber, manufacturer, storage);
            phoneList.add(mobilePhone);
        } else {
//        Declare Operating System variable
            String operatingSystem;
//      Random Operating System
            if (randomNumber == 2) {
//            If manufacturer is Apple then the Operating System is iOS  
                operatingSystem = operatingSystems[1];
            } else {
//            If not Apple choose random Operating System expect iOS
                upperBound = 2;
                int randomNumber2 = random.nextInt(upperBound);
                if (randomNumber2 == 0) {
                    operatingSystem = operatingSystems[0];
                } else {
                    operatingSystem = operatingSystems[2];
                }
            }
//        Declare new SmartPhone
            mobilePhone = new SmartPhone(batterySize, screenSize, phoneNumber, manufacturer, storage, camera, operatingSystem);

//        Import SmartPhone to ArrayList
            phoneList.add(mobilePhone);
        }
        return mobilePhone;
    }
}
