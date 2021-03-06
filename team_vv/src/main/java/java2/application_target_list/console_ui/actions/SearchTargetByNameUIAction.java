package java2.application_target_list.console_ui.actions;

import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.SearchTargetByNameRequest;
import java2.application_target_list.core.services.SearchTargetByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.SearchTargetByNameResponse;


import java.util.Scanner;

@Component
public class SearchTargetByNameUIAction implements UIAction {

    @Autowired private SearchTargetByNameService searchTargetByNameService;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true){
            String targetName = getNameFromUser();

            printOrderingMessage();
            int orderingFromUser = getNumberFromUser();

            printPagingMessage();
            int pagingFromUser = getNumberFromUser();

            SearchTargetByNameResponse response;

            if (isOrderingNeeded(orderingFromUser) && isPagingNeeded(pagingFromUser)){
                String orderByFromUser = getOrderByFromUser();
                String orderDirectionFromUser = getOrderingDirectionFromUser();
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchTargetByNameRequest request = createRequestWithOrderingAndPaging(targetName,
                                                                                       orderByFromUser, orderDirectionFromUser,
                                                                                       pageNumber, pageSize);
                response = createResponse(request);
            } else if (isOrderingNeeded(orderingFromUser) && !isPagingNeeded(pagingFromUser)){
                String orderByFromUser = getOrderByFromUser();
                String orderDirectionFromUser = getOrderingDirectionFromUser();
                SearchTargetByNameRequest request = createRequestWithOrdering(targetName, orderByFromUser, orderDirectionFromUser);
                response = createResponse(request);
            } else if (isPagingNeeded(pagingFromUser) && !isOrderingNeeded(orderingFromUser)){
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchTargetByNameRequest request = createRequestWithPaging(targetName, pageNumber, pageSize);
                response = createResponse(request);
            } else {
                SearchTargetByNameRequest request = createRequest(targetName);
                response = createResponse(request);
            }


            if (response.hasErrors()) {
                printResponseErrors(response);
            } else {
                printResponseResultMessage(response);
                break;
            }
        }
    }

    private SearchTargetByNameRequest createRequestWithOrdering(String targetName,String  orderBy,String orderDirection){
        Ordering ordering = new Ordering(orderBy, orderDirection);
        return new SearchTargetByNameRequest(targetName, ordering);
    }

    private SearchTargetByNameRequest createRequestWithPaging(String targetName,Integer pageNumber, Integer pageSize){
        Paging paging = new Paging(pageNumber, pageSize);
        return new SearchTargetByNameRequest(targetName, paging);
    }

    private boolean isOrderingNeeded(int number){
        return number == 1;
    }

    private boolean isPagingNeeded(int number){
        return number == 2;
    }

    private void printPagingMessage(){
        System.out.println("Show all target list?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
    }

    private void printOrderingMessage(){
        System.out.println("Need to sort target list?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
    }

    private String getOrderByFromUser(){
        System.out.println("ENTER sort by (name, description or deadline):  ");
        return scr.nextLine();
    }


    private String getOrderingDirectionFromUser(){
        System.out.println("ENTER sort direction (ASCENDING or DESCENDING): ");
        return scr.nextLine();
    }

    private Integer getNumberFromUser(){
        return Integer.parseInt(scr.nextLine());
    }

    private void printResponseResultMessage(SearchTargetByNameResponse response){
        if (response.getTargetsList().isEmpty()){
            System.out.println("----------");
            System.out.println("Target not found!");
            System.out.println("----------");
        } else {

            for (Target target : response.getTargetsList()) {
                System.out.println(target.getId() + ". " +
                        target.getName() + " [" + target.getDescription() + "] " +
                        target.getDeadline() + " days");
            }
            System.out.println("----------");
        }
    }

    private void printResponseErrors(SearchTargetByNameResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private SearchTargetByNameResponse createResponse(SearchTargetByNameRequest request){
        return searchTargetByNameService.execute(request);
    }

    private SearchTargetByNameRequest createRequest(String targetName){
        return new SearchTargetByNameRequest(targetName);
    }

    private SearchTargetByNameRequest createRequestWithOrderingAndPaging(String targetName,
                                                                         String orderBy, String orderDirection,
                                                                         Integer pageNumber, Integer pageSize){
        Paging paging = new Paging(pageNumber, pageSize);
        Ordering ordering = new Ordering(orderBy, orderDirection);
        return new SearchTargetByNameRequest(targetName, ordering, paging);
    }

    private String getNameFromUser(){
        System.out.print("Enter target name: ");
        return scr.nextLine();
    }

    private Integer getPageNumberFromUser(){
        System.out.print("Enter page number: ");
        return Integer.parseInt(scr.nextLine());
    }

    private Integer getPageSizeFromUser(){
        System.out.print("Enter page size: ");
        return Integer.parseInt(scr.nextLine());
    }

}
