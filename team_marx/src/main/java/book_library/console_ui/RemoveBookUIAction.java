package book_library.console_ui;

import book_library.services.RemoveBookService;

import java.util.Scanner;

public class RemoveBookUIAction implements UIAction{

    private RemoveBookService removeBookService;

    public RemoveBookUIAction(RemoveBookService removeBookService) {
        this.removeBookService = removeBookService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id to remove:");
        Long bookId = Long.parseLong(scanner.nextLine());
        removeBookService.execute(bookId);
        System.out.println("Your book was removed from the list.");
    }
}