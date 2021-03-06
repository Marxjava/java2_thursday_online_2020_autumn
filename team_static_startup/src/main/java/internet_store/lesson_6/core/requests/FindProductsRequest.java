package internet_store.lesson_6.core.requests;

public class FindProductsRequest {

    private final String name;
    private final String description;
    private Ordering ordering;
    private Paging paging;

    public FindProductsRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FindProductsRequest(String name, String description, Ordering ordering) {
        this.name = name;
        this.description = description;
        this.ordering = ordering;
    }

    public FindProductsRequest(String name, String description, Paging paging) {
        this.name = name;
        this.description = description;
        this.paging = paging;
    }

    public FindProductsRequest(String name, String description, Ordering ordering, Paging paging) {
        this.name = name;
        this.description = description;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isDescriptionProvided() {
        return this.description != null && !this.description.isEmpty();
    }


}
