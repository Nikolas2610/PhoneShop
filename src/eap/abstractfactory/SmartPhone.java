package eap.abstractfactory;

import eap.simulate.DelaySimulator;
import java.awt.Dimension;


/*Η κλάση αυτή επεκτείνει την κλάση Phone.
Διαθέτει επιπλέον τα χαρακτηριστικά camera (της δοθείσας αντίστοιχης κλάσης) και το αλφαριθμητικό operatingSystem
Θα πρέπει εντός της υλοποίησής της να υποσκελίσετε (να κάνετε override) τη μέθοδο toString η οποία έχει κληρονομηθεί από την κλάση Phone.
Ωστόσο, στη νέα υλοποίηση της μεθόδου toString Θα πρέπει ο κώδικάς σας να χρησιμοποιεί την κληρονομηθείσα μέθοδο και να συμπληρώνει το
επιστρεφόμενο αποτέλεσμα με τα νέα πεδία που εισήχθησαν στην κλάση SmartPhone.*/
public class SmartPhone extends Phone {
// Like Featured Phones

    private final int batterySize;
    private final Dimension screenSize;
    private final String phoneNumber;
    private final String manufacturer;
    private final int storage;
//    Import Camera variable
    private final int camera;
//    Import operation system variable
    private final String operatingSystem;

//    Create Constructor
    public SmartPhone(int batterySize, Dimension screenSize, String phoneNumber, String manufacturer, int storage, int camera, String operatingSystem) {
        this.batterySize = batterySize;
        this.screenSize = screenSize;
        this.phoneNumber = phoneNumber;
        this.manufacturer = manufacturer;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.camera = camera;
    }

    @Override
    public int getBatterySize() {
        return batterySize;
    }

    @Override
    public Dimension getScreenSize() {
        return screenSize;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public int getStorage() {
        return storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public int getCamera() {
        return camera;
    }

//    Override toString method
    @Override
    public String toString() {
        return "Battery(mAh): "
                + getBatterySize() + "\n"
                + "Screen: "
                + getScreenSize() + "\n"
                + "Number: "
                + getPhoneNumber() + "\n"
                + "Manufacturer: "
                + getManufacturer() + "\n"
                + "Storage(GB): "
                + getStorage() + "\n"
                + "Camera: "
                + getCamera() + " MP" + "\n"
                + "Operation System: "
                + getOperatingSystem();

    }

}
