package lv.javaguru.java2.library.integrationtests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lv.javaguru.java2.library.config.BookListConfiguration;
import lv.javaguru.java2.library.core.services.EmailServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BookListConfiguration.class})
public class SpringContextTest {

	@Autowired private ApplicationContext appContext;

	@Autowired(required = false) private EmailServer emailServer;

	@Test
	public void start() {
		assertNotNull(appContext);
	}

}
