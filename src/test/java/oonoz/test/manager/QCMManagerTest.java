package oonoz.test.manager;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.OonozApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=OonozApplication.class)
@TestPropertySource(locations = {"classpath:/test/config.properties"})
public class QCMManagerTest {
	
	

}
