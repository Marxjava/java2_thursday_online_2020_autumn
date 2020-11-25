package internet_store.lesson_1.uiaction;

import internet_store.lesson_1.ProductDatabase;

import java.util.Scanner;

public class DeleteByIdUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public DeleteByIdUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for deleting: ");
        Long id = myInput.nextLong();
        boolean productDeleted = productDatabase.delete(id);

        if (productDeleted) {
            System.out.println("\nProduct with ID = " + id + " deleted");
        } else {
            System.out.println("\nProduct with ID = " + id + " is not in the database");
        }
    }

}
