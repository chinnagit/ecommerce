package com.bala.ecommerce.cart;

import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.repository.CartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartRepositoryTest {

	@Mock
	CartRepository dataServiceMock;


	@Test
	public void testFindTheGreatestFromAllData() {
		Collection<Cart> it = new ArrayList<>();
		when(dataServiceMock.findAll()).thenReturn(it);
		assertTrue(dataServiceMock.findAll()!= null);
	}

}
