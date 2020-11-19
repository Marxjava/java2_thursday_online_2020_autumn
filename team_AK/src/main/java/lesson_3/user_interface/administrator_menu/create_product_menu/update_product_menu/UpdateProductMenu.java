package lesson_3.user_interface.administrator_menu.create_product_menu.update_product_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateProductMenu {
    @Getter
    private long userUpdatedProductIdInput;

    public void showMenuUpdateProduct() {
        boolean errorInput;
        System.out.println("Update product");
        System.out.println("Enter ID number");
        do {
            try {
                userUpdatedProductIdInput = new Scanner(System.in).nextLong();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}
