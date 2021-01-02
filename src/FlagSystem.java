import java.security.PolicySpi;
import java.util.ArrayList;

public class FlagSystem {

    public static void customFlagNormal(int role){
        if(role == 1)
            customFlagNormalCust();
        else
            customFlagNormalShop();
        System.out.println("Flag Successfully.\n");

    }

    public static void customFlagNormalCust() {
        int choice;
        CustReport custReport = new CustReport();
        custReport.display();
        System.out.print("Please enter No. to be flag Normal: ");
        choice = InputValid.checkRange(1, Customer.custs.size());

        Customer.custs.get(choice-1).setStatus("Normal");
        Customer.Serialize();
    }

    public static void customFlagNormalShop() {
        int choice;

        ShopReport shopReport = new ShopReport();
        shopReport.display();
        System.out.print("Please enter No. to be flag Normal: ");
        choice = InputValid.checkRange(1,Shop.shops.size());

        Shop.shops.get(choice-1).setStatus("Normal");
        Shop.Serialize();
    }

}
