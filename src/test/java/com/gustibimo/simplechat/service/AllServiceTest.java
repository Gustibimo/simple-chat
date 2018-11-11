package com.gustibimo.simplechat.service;

import com.gustibimo.simplechat.user.service.UserMapperTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = {
		UserMapperTest.class,
		ChatServiceTest.class,
		ChatMessageServiceImplTest.class
})
public class AllServiceTest {

}
