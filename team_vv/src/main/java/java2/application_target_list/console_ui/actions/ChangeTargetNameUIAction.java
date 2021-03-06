package java2.application_target_list.console_ui.actions;

import java2.application_target_list.core.requests.ChangeTargetNameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.services.ChangeTargetNameService;

import java.util.Scanner;

@Component
public class ChangeTargetNameUIAction implements UIAction {

    @Autowired private ChangeTargetNameService changeTargetNameService;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            Long targetId = getIdFromUser();
            String newTargetName = getNewNameFromUser();

            ChangeTargetNameRequest request = createRequest(targetId, newTargetName);
            ChangeTargetNameResponse response = createResponse(request);

            if (response.hasErrors()) {
                printResponseErrors(response);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Target name was changed!");
        System.out.println("----------");
    }

    private void printResponseErrors(ChangeTargetNameResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private ChangeTargetNameResponse createResponse(ChangeTargetNameRequest request){
        return changeTargetNameService.execute(request);
    }

    private ChangeTargetNameRequest createRequest(Long targetId, String newTargetName){
        return new ChangeTargetNameRequest(targetId, newTargetName);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private String getNewNameFromUser(){
        System.out.print("Enter new target name: ");
        return scr.nextLine();
    }

}
