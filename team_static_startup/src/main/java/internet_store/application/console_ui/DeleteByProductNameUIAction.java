package internet_store.application.console_ui;

import internet_store.application.core.services.DeleteProductService;

import java.util.Scanner;

public class DeleteByProductNameUIAction implements UIAction {

    private final DeleteProductService deleteProductService;

    public DeleteByProductNameUIAction(DeleteProductService deleteProductService) {
        this.deleteProductService = deleteProductService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Deleting product by name : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();

        boolean productDeleted = deleteProductService.deleteByProductName(productName);
        if (productDeleted) {
            System.out.println("\nProduct with name = " + productName + " deleted");
        } else {
            System.out.println("\nProduct with name = " + productName + " is not in the database");
        }
    }

}