package eap.observer;

import eap.abstractfactory.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneOrderHandler {

    private static final List<PhoneCreationListener> phoneCreationListeners = new ArrayList<>();
    private static final List<Phone> phoneList = new ArrayList<>();

    public static void addPhone(Phone phone) {
        phoneList.add(phone);
    }

    public static void removePhone(Phone phone) {
        phoneList.remove(phone);
    }

    public static boolean phoneExistsInList(Phone phone) {
        return phoneList.contains(phone);
    }

    public static void buildPhones() {
        System.out.println("\n##########-Phone Build Process Started-##########");
        List<Phone> phones = new ArrayList<>(phoneList);//Make a copy of the phones that should be created
        for (Phone phone : phones) {
            if (phone instanceof FeaturePhone) {
                PhoneFactory.getPhone(new FeaturePhoneFactory(phone.getBatterySize(), phone.getScreenSize(),
                        phone.getPhoneNumber(), phone.getManufacturer(), phone.getStorage()));
                System.out.println("\n$$$$-New Phone-$$$$");
                System.out.println(phone + "\nHas been created");
                System.out.println("$$$$-----------$$$$");
            } else if (phone instanceof SmartPhone) {
                SmartPhone smartPhone = (SmartPhone) phone;
                PhoneFactory.getPhone(new SmartPhoneFactory(smartPhone.getBatterySize(), smartPhone.getScreenSize(),
                        smartPhone.getPhoneNumber(), smartPhone.getManufacturer(), smartPhone.getStorage(),
                        smartPhone.getCamera(), smartPhone.getOperatingSystem()));
                System.out.println("\n$$$$-New Phone-$$$$");
                System.out.println(phone + "\nHas been created");
                System.out.println("$$$$-----------$$$$");
            }
            notifyListeners(phone);
        }
        printCreatedPhoneSpecsToTextFile(phones);
        System.out.println("\n########-Phone Specs saved to output.txt-########");
        System.out.println("###########-Phone Build Process Ended-###########");
    }

    //?? ?????????????? ???????? ???? ?????????????????? ???? ???????????? ????????????????????????????, ???????? ?????? ?????? ?????????? Phone ?????? ?????????????? ??????????????????
    //???????? ?????????????? ????????????????????
    private static void printCreatedPhoneSpecsToTextFile(List<Phone> phones) {
//        Create File
        File f = new File("output.txt");
//        Create counter
        int i = 1;

        try (FileWriter fw = new FileWriter(f)) {
//          For each loop for the phones
            for (Phone phone : phones) {
//                Write counter
                fw.write(i + ".\n");
//                Write phone and change line
                fw.write(phone.toString() + "\n");
//                Increase counter
                i++;
            }
        } catch (IOException e) {
//            Print errors 
            e.printStackTrace();
        }
    }

    public static void addListener(PhoneCreationListener phoneCreationListener) {
        phoneCreationListeners.add(phoneCreationListener);
    }

    public static void removeListener(PhoneCreationListener phoneCreationListener) {
        phoneCreationListeners.remove(phoneCreationListener);
    }

    private static void notifyListeners(Phone phone) {
        List<PhoneCreationListener> tempPhoneCreationListeners = new ArrayList<>(phoneCreationListeners);
        for (PhoneCreationListener listener : tempPhoneCreationListeners) {
            listener.update(phone);
        }
    }
}
