package internet_store.user_interface.administrator_menu.create_product_menu.delete_product_menu;

import org.springframework.stereotype.Component;
import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class DeleteProductMenu {
    @Getter
    private long userDeletedProductIdInput;

    public void showMenuDeleteProduct() {
        boolean errorInput;
        System.out.println("Delete product");
        System.out.println("Enter ID number");
        do {
            try {
                userDeletedProductIdInput = new Scanner(System.in).nextLong();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}