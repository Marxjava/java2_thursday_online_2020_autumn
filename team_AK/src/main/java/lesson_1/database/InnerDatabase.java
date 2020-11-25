package lesson_1.database;

import internet_store.core.domain.Product;

public interface InnerDatabase {

    void add(Product product);
    void deleteById(Long id);
    void updateById(Long id);
    void showReport();
}