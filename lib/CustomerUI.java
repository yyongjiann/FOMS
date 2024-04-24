import java.util.Scanner;

public class CustomerUI {

	private String branchName;
	private IDataManager<Order,Integer> orderDB;

	public void createOrder() {
		DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
		DataManagerForFoodItem foodItemDB = DataManagerForFoodItem.getInstance();
		Display displayformatter = new Display();
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        boolean takeaway;
        while (!valid) {
            try {
                System.out.println("Please select an option. ");
                System.out.println("1. Dine-in");
                System.out.println("2. Takeaway");
                String option = sc.nextLine();

                switch (option) {
                    case "1":
                        valid = true;
                        takeaway = false;
                        break;
                    case "2":
                        valid = true;
                        takeaway = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
        Order newOrder = new Order(orderDB.getAll().size() + 1, takeaway);
		new OrderControl(orderDB, foodItemDB, displayformatter, newOrder, branchName);
        sc.close();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void checkOrder(int orderID) {
		System.out.println(orderDB.find(orderID).toString());
	}

	public void showCustomerOptions() {
		Scanner sc = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("Welcome to " + branchName);
                System.out.println("Please select one of the following options.");
                System.out.println("1. Create Order");
                System.out.println("2. Check Order Status");
                String option = sc.nextLine();

                switch (option) {
                    case "1":
                        valid = true;
                        createOrder();
                        break;
                    case "2":
                        System.out.println("What is your order ID? ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        checkOrder(id);;
                        valid = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        sc.close();
        
	}

	/**
	 * 
	 * @param branchName
	 */
	public CustomerUI(String branchName, IDataManager<Order,Integer> orderDB) {
		this.branchName = branchName;
		this.orderDB = orderDB;

        showCustomerOptions();
	}

}