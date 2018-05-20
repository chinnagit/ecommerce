package com.bala.ecommerce.cart;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.ecommerce.cart.Application;
import com.ecommerce.cart.entity.User;
import com.ecommerce.cart.repository.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	  TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
	@RunWith(SpringJUnit4ClassRunner.class)
	@SpringApplicationConfiguration(classes = Application.class)
//	@DatabaseSetup(ItemRepositoryIT.DATASET)
//	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { ItemRepositoryIT.DATASET })
	@DirtiesContext
	public class UserRepositoryTest {
		@Autowired
		private UserRepository userRepository;
		@Test
		public void findCheckedShouldReturnTwoItems() {
			userRepository.findAll();
			List<User> users = new ArrayList<>();
			Iterable<User> usersIt = userRepository.findAll();
			usersIt.forEach(users::add);
			users.forEach(user -> System.out.println("User: "+user.getName()));
			assertTrue(users.size() > 0);
//		  assertThat(repository.findChecked())
//		    .hasSize(2)
//		    .extracting(DESCRIPTION_FIELD)
//		    .containsOnly(FIRST_ITEM, THIRD_ITEM);
		}
	}


