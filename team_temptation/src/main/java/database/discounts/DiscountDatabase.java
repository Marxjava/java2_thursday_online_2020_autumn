package database.discounts;

import domain.Discounts;

import java.util.List;

public interface DiscountDatabase {

    boolean add (Discounts discount);

    boolean remove (String discountName);

    List<Discounts> getDiscountsList();

    List<Discounts> searchDiscounts();

}
