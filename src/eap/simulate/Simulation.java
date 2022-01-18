package eap.simulate;

import eap.observer.PhoneOrderHandler;
import eap.abstractfactory.FeaturePhone;
import eap.abstractfactory.Phone;
import eap.abstractfactory.SmartPhone;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

import java.awt.Dimension;

public class Simulation {

    //Fixed array of Greek names
    static final String[] names = {"George", "Maria", "Dimitris", "John", "Katerina",
        "Panagiotis", "Konstantinos", "Christina", "Eleni", "Petros"};

    static ArrayList<String> waitingClients = new ArrayList<>();

    // Στη μέθοδο main το πρόγραμμα αρχικά ζητά από τον χρήστη να δηλώσει τον αριθμό
    // των κινητών τηλεφώνων που πρόκειται να δοθούν στη συνέχεια στη γραμμή παραγωγής,
    // καθώς και τον αριθμό των πελατών που θα δημιουργηθούν
    // και θα αναμείνουν για την ενδεχόμενη παραγωγή των τηλεφώνων που τους ενδιαφέρουν
    public static void main(String[] args) {

//        Declare Variables
        int numberOfPhones;
        int numberOfClients;
        //Import Scanner
        Scanner sc = new Scanner(System.in);
//        Get number of phones to be ordered
        System.out.print("Please enter number of phones to be ordered:");
        numberOfPhones = sc.nextInt();
        while (true) {
            //        Get number of clients that wait a new phone
            System.out.print("Please enter number of clients waiting for new phones (MAX:10):");
            numberOfClients = sc.nextInt();
            if (numberOfClients >= 0 && numberOfClients <= 10) {
                break;
            }
            System.out.print("The answer is not valid!");
        }
//      Print number of phones and number of clients
        System.out.print(numberOfPhones);
        System.out.println(" phone orders have been placed by the PhoneShop!");
        System.out.print(numberOfClients);
        System.out.println(" clients are waiting to buy a new phone!");

//        Create Arraylist for Clients
        ArrayList<Client> clients = new ArrayList<>();
//        Create random phones. [Feature phone or Smart phone]
        Class[] phoneClass = {FeaturePhone.class, SmartPhone.class};
        Random random = new Random();

        int randomName;
        int randomPhone;
        for (int i = 0; i < numberOfClients; i++) {
//            Choose Random names
            randomName = random.nextInt(10);
//            Choose Random Phone
            randomPhone = random.nextInt(2);
//          Put Name and Phone 
            waitingClients.add(names[randomName]);
            clients.add(new Client(names[randomName], phoneClass[randomPhone]));
        }

//        Create PhoneShop 
        PhoneShop kotsovolos = new PhoneShop();
//        Create ArrayList for Phones
        Phone tempPhone;

//        Create phoneSpec
        for (int i = 0; i < numberOfPhones; i++) {
            tempPhone = kotsovolos.createPhoneSpec();
            PhoneOrderHandler.addPhone(tempPhone);
        }
//        Build Phones
        PhoneOrderHandler.buildPhones();

//        Print how many clients did not get a phone
        System.out.println("%%%%%----Report----%%%%%");
        System.out.println(waitingClients.size() + " clients did not get a phone...");
        System.out.println("%%%%%--------------%%%%%");

    }

}
