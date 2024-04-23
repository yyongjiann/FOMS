import java.util.Scanner;
public class AdminForPayment extends Admin implements IAdminForPayment {
	private IDataManager paymentDB;
	public AdminForPayment(){
	    this.paymentDB=DataManagerForPayment.getInstance();
	}
	public void addPaymentMethod(){
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Select option");
	    System.out.println("1.Online");
	    System.out.println("2.Credit");
	    int choice=sc.nextInt();
	    IPayment paymentMode;
	    if(choice==1){
		    paymentMode=new Online();
	    }else if(choice==2){
		    paymentMode=new Credit();
	    }else{
		    System.out.println("Invalid payment option");
		    return;
	    }
	    paymentDB.add(paymentMode);
	}
	public void removePaymentMethod(){
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Enter the payment method to remove");
	    String paymentMethod=sc.nextLine();
	    IPayment paymentMethodClass=paymentDB.find(paymentMethod);
	    paymentDB.delete(paymentMethodClass);
	}
}
