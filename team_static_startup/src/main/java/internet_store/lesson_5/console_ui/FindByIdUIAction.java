package internet_store.lesson_5.console_ui;

import internet_store.lesson_5.core.domain.Product;
import internet_store.lesson_5.core.requests.FindByIdRequest;
import internet_store.lesson_5.core.responses.FindByIdResponse;
import internet_store.lesson_5.core.services.FindByIdService;

import java.util.Optional;
import java.util.Scanner;

public class FindByIdUIAction implements UIAction {

    private final FindByIdService findByIdService;

    public FindByIdUIAction(FindByIdService findByIdService) {
        this.findByIdService = findByIdService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for searching : ");
        String id = myInput.nextLine();

        FindByIdRequest request = new FindByIdRequest(id);
        FindByIdResponse response = findByIdService.execute(request);

        Optional<Product> foundProduct = response.getProductFoundById();

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else { if (response.getProductFoundById().isEmpty()) {
            System.out.println("\nNo product with ID = " + id + " in the DataBase");
        } else {
            Product product = foundProduct.get();
            System.out.println("Found product in the database :");
            System.out.print(product.toString() + "\n");
        }
        }
    }


}
