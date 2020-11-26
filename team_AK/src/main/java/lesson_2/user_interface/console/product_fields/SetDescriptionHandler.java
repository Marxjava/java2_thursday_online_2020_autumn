package lesson_2.user_interface.console.product_fields;

import internet_store.core.domain.Product;

import java.util.Scanner;

public class SetDescriptionHandler extends InputHandler {
    public SetDescriptionHandler() {
        super();
    }

    @Override
    public void getInput(Product product) {
        System.out.println(ProductFieldsResources.DESCRIPTION.getText());
        String userInput = new Scanner(System.in).nextLine();
        product.setDescription(userInput);
    }
}