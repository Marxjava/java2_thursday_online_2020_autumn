package book_library.console_ui;

public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("************");
        System.out.println("* Good by! *");
        System.out.println("************");
        System.exit(0);
    }
}
