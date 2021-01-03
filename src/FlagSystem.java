import java.security.PolicySpi;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class FlagSystem {

    public static void flagPatient(int choice) {
        Visit chosen = Visit.visits.get(choice - 1);

        int shopID = chosen.getShopID();
        int custID = chosen.getCustID();
        LocalDateTime CaseDT = chosen.getDt();
        LocalDateTime BfCaseDT = CaseDT.minusHours(1);
        LocalDateTime AfCaseDT = CaseDT.plusHours(1);

        Shop.shops.get(shopID - 1).setStatus("Case");
        Shop.Serialize();

        ArrayList<Visit> tmp = new ArrayList<Visit>();
        for (Visit v : Visit.visits)
            if (v.getShopID() == shopID)
                tmp.add(v);


        for (Visit v :tmp) {
            int visitCustID = v.getCustID();
            LocalDateTime VisitDT = v.getDt();
            if((VisitDT.isBefore(CaseDT) && VisitDT.isAfter(BfCaseDT))
                    || (VisitDT.isAfter(CaseDT) && VisitDT.isBefore(AfCaseDT))) {
                Customer.custs.get(visitCustID - 1).setStatus("Close");
            }
        }

        Customer.custs.get(custID - 1).setStatus("Case");
        Customer.Serialize();
    }


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
