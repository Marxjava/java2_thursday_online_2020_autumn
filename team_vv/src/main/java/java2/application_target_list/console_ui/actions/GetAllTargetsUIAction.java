package java2.application_target_list.console_ui.actions;

import java2.application_target_list.core.requests.GetAllTargetsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.domain.Target;
import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.GetAllTargetsResponse;
import java2.application_target_list.core.services.GetAllTargetsService;


@Component
public class GetAllTargetsUIAction implements UIAction {

    @Autowired private GetAllTargetsService getAllTargetsService;

    @Override
    public void execute() {
        GetAllTargetsRequest request = createRequest();
        GetAllTargetsResponse response = createResponse(request);

        if (isTargetListEmpty(response)) {
            printResponseMessage();
        } else {
           printTargetList(response);
        }

    }

    private void printTargetList(GetAllTargetsResponse response){
        for (Target target : response.getTargetList()){
            System.out.println(target.getId() + ". " +
                    target.getName() + " [" + target.getDescription() + "] " +
                    target.getDeadline() + " days");
        }
        System.out.println("----------");
    }

    private void printResponseMessage(){
        System.out.println("----------");
        System.out.println("Targets list is empty");
        System.out.println("----------");
    }

    private boolean isTargetListEmpty(GetAllTargetsResponse response){
        return response.getTargetList().size() == 0;
    }

    private GetAllTargetsResponse createResponse(GetAllTargetsRequest request){
        return getAllTargetsService.execute(request);
    }

    private GetAllTargetsRequest createRequest(){
        return new GetAllTargetsRequest();
    }

}
