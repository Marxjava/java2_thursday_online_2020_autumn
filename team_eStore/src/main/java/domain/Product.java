package domain;

import java.util.Comparator;
import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private String description;
    private String price;

    public Product(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name)
                && description.equals(product.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, price);
    }

    public static Comparator<Product> idComparator = new Comparator<Product>() {
        public int compare(Product p1, Product p2) {
            long productId1 = p1.getId();
            long productId2 = p2.getId();
            return (int)(productId1 - productId2);
        }
    };

    @Override
    public String toString() {
        return "Product:" +
                " id = " + id +
                ", name = " + name +
                ", description = " + description +
                ", price = " + price + "Eur";
    }
}
