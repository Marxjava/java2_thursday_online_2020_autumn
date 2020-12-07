package internet_store_tests.dependency_injection;


import internet_store.core.services.customer.validators.DeleteCustomerRequestValidator;
import internet_store.dependency_injection.ApplicationContext;
import internet_store.dependency_injection.ClassFinder;
import internet_store.dependency_injection.DIComponentCreator;
import internet_store.dependency_injection.DIComponentFiler;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DIComponentCreatorTest {

    ApplicationContext applicationContext = new ApplicationContext();
    ClassFinder classFinder = new ClassFinder();
    DIComponentFiler diComponentFiler = new DIComponentFiler();
    DIComponentCreator diComponentCreator = new DIComponentCreator();

    @Test
    public void test() throws IOException, ClassNotFoundException{
        List<Class> allClasses = classFinder.findClassesInsidePackage("internet_store.core.services.customer.validators");
        List<Class> diClasses = diComponentFiler.filter(allClasses);
        diComponentCreator.create(applicationContext, diClasses);

        assertTrue(!applicationContext.getBeans().isEmpty());

        /*assertTrue(applicationContext.getBeans().size() == 5);*/
        assertTrue(applicationContext.getBeans().containsKey(DeleteCustomerRequestValidator.class));

    }
}