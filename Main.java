import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

       Product p1 = new Product(1, "Laptop", 50000, 5, "Electronics");

Product p2 = new Product(2, "Mouse", 500, 10, "Accessories");

Product p3 = new Product(3, "Keyboard", 1000, 8, "Accessories");

        ShoppingCart cart = new ShoppingCart();

        int choice;

        do {

            System.out.println("\n===== E-Commerce Shopping Cart =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Cart");
            System.out.println("3. Remove Product");
            System.out.println("4. View Total");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                // 🟢 ADD PRODUCT
                case 1:

                    System.out.println("\nAvailable Products");
                    System.out.println("1. Laptop - Rs.50000");
                    System.out.println("2. Mouse - Rs.500");
                    System.out.println("3. Keyboard - Rs.1000");

                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    if (id == 1) {
                        cart.addProduct(p1, qty);
                    } else if (id == 2) {
                        cart.addProduct(p2, qty);
                    } else if (id == 3) {
                        cart.addProduct(p3, qty);
                    } else {
                        System.out.println("Invalid Product ID");
                    }

                    break;

                // 🟢 VIEW CART
                case 2:
                    System.out.println(cart.viewCart());
                    break;

                // 🟢 REMOVE PRODUCT
                case 3:
                    System.out.print("Enter Product ID to Remove: ");
                    int removeId = sc.nextInt();
                    cart.removeProduct(removeId);
                    break;

                // 🟢 TOTAL
                case 4:
                    System.out.println("Total = Rs." + cart.getTotal());
                    break;

                // 🟢 CHECKOUT
                case 5:
                    double total = cart.checkout();
                    System.out.println("Order Placed!");
                    System.out.println("Total Paid = Rs." + total);
                     System.out.println("Thank you for shopping!");
                    break;

                // 🟢 EXIT
                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);

        sc.close();
    }
}