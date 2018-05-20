package com.bala.ecommerce.cart;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.repository.CartRepository;

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
