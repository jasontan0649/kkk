import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Admin signIn() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Username: ");
            String username = input.nextLine();
            System.out.println("Password: ");
            String password = input.nextLine();
            Admin adm = Admin.getAdminByName(username);
            if(adm == null || !adm.getPassword().equals(password)) {
                System.out.println("Incorrect Password / Username.\nPlease try again.");
                continue;
            }
            return adm;
        }
    }

    public static Admin signUp() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your username: ");
            String username = input.nextLine();
            if(username.contains(" ")) {
                System.out.println("Username should not contain space.\nPlease try again");
                continue;
            }
            System.out.println("Enter your password(max 16 characters): ");
            String password = input.nextLine();
            if (password.length() > 16) {
                System.out.println("Exceed max character.\nPlease enter only 16 characters.");
                continue;
            }
            return new Admin(username, password);
        }
    }

    public static void AdminInterface () {
        while(true){
            System.out.println("Please select below operation.");
            System.out.println("1. View master visit history");
            System.out.println("2. View all customers status");
            System.out.println("3. View all shop status");
            System.out.println("4. Flag Customer Patient");
            System.out.println("5. Custom Flag Normal");
            System.out.println("6. Generate Random 30 visits");
            System.out.println("7. Exit");


            Scanner input = new Scanner(System.in);

            int choice = InputValid.checkRange(1, 7);

            switch (choice){
                case 1: viewMasterHistory(); break;
                case 2: viewCustStatus(); break;
                case 3: viewShopStatus(); break;
                case 4: flagPatient(); break;
                case 5: customFlagNormal(); break;
                case 6: Initializer.preVisit(); break;
                case 7: return;
            }
        }
    }

    public static void viewMasterHistory() {
        VisitReport visitReport = new VisitReport();
        visitReport.display();
        System.out.println("Export into csv file? Y/N");
        String ch = InputValid.checkValidChar();
        if (ch.equals("Y"))
            visitReport.exportCSV();
    }

    public static void viewCustStatus(){
        CustReport custReport = new CustReport();
        custReport.display();
        System.out.println("Export into csv file? Y/N");
        String ch = InputValid.checkValidChar();
        if (ch.equals("Y"))
            custReport.exportCSV();

    }

    public static void viewShopStatus(){
        ShopReport shopReport = new ShopReport();
        shopReport.display();
        System.out.println("Export into csv file? Y/N");
        String ch = InputValid.checkValidChar();
        if (ch.equals("Y"))
            shopReport.exportCSV();

    }

    public static void flagPatient(){
        new VisitReport().display();
        while(true){
            System.out.println("Choose from visit history to be flagged:");
            System.out.println("(Please input one by one && input -1 to stop)");
            int choice = InputValid.checkRange(-1,Visit.visits.size());
            if(choice == -1)
                return;
            else
                FlagSystem.flagPatient(choice);
        }
    }

    public static void customFlagNormal(){
        while (true){
            System.out.println("1. Customer");
            System.out.println("2. Shop");
            System.out.println("3. back");

            int role = InputValid.checkRange(1,3);
            if(role == 3)
                break;

            FlagSystem.customFlagNormal(role);

            viewCustStatus();
            viewShopStatus();
        }
    }

}
