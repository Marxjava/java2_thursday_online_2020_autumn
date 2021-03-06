package java2.application_target_list.core.acceptancetests;

import java2.application_target_list.core.requests.AddTargetRequest;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.SearchTargetByNameRequest;
import java2.application_target_list.core.services.SearchTargetByNameService;
import java2.application_target_list.core.responses.SearchTargetByNameResponse;
import java2.application_target_list.core.services.AddTargetService;
import java2.application_target_list.config.TargetListConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SearchTargetByNameAcceptanceTests {

    private ApplicationContext applicationContext;
    private SearchTargetByNameService searchTargetByNameService;

    @Before
    public void setup(){
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        searchTargetByNameService = applicationContext.getBean(SearchTargetByNameService.class);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);

        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 6);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("wdc", "sda", 156);

        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
        addTargetService.execute(addTargetRequest3);
        addTargetService.execute(addTargetRequest4);

        ReflectionTestUtils.setField(searchTargetByNameService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchTargetByNameService, "pagingEnabled", true);
    }


    @Test
    public void shouldReturnList() {
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name");
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByNameAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByNameDescending() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionDescending() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionAscending() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineAscending() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineDescending() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 3);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getName(), "name2");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(1).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(1).getDeadline()), Optional.of(4));
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(2).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(2).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByNamePaging() {
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByNameAscendingAndPaging() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByNameDescendingAndPaging() {
        Ordering ordering = new Ordering("name", "DESCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionDescendingWithPaging() {
        Ordering ordering = new Ordering("description", "DESCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
    }

    @Test
    public void shouldReturnListWithOrderingByDescriptionAscendingWithPaging() {
        Ordering ordering = new Ordering("description", "ASCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineAscendingWithPaging() {
        Ordering ordering = new Ordering("deadline", "ASCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering, paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name1");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description1");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(1));
    }

    @Test
    public void shouldReturnListWithOrderingByDeadlineDescendingWithPaging() {
        Ordering ordering = new Ordering("deadline", "DESCENDING");
        Paging paging = new Paging(1,1);
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("name", ordering,paging);
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "name3");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(6));
    }




}
