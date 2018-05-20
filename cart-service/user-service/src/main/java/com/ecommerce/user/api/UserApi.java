/**
 * 
 */
package com.ecommerce.user.api;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.model.User;


/**
 * @author chinnb
 *
 */

@RestController
public class UserApi {

	private List<User> users;
	
	protected Logger logger = Logger.getLogger(UserApi.class.getName());
	
	public UserApi() {
		users = new ArrayList<>();
		users.add(new User("bala", "abc123"));
		users.add(new User("kavitha", "abc123"));
		users.add(new User("kanish", "abc123"));
	}
	
	
    
	@PreAuthorize("\"#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/user/{name}")
    @ResponseBody
	public User findByName(@PathVariable("name") String name) {
		logger.info(String.format("User.findByName(%s)", name));
		return users.stream().filter(it -> it.getName().equals(name)).findFirst().get();
	}
	
//	@PreAuthorize("\"#oauth2.hasScope('foo') and #oauth2.hasScope('read') and hasRole('ROLE_ADMIN')")
	
	@PreAuthorize("\"#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    @ResponseBody
	public List<User> findll() {
		System.out.println("################# in user service ##############");
		logger.info("User.findAll()");
		return users;
	}
    
//	@RequestMapping("/user/{name}")
//	public User findByName(@PathVariable("name") String name) {
//		logger.info(String.format("User.findByName(%s)", name));
//		return users.stream().filter(it -> it.getName().equals(name)).findFirst().get();
//	}
//	
//	
//	@RequestMapping("/user")
//	public List<User> findAll() {
//		logger.info("User.findAll()");
//		return users;
//	}
	
	@RequestMapping("/user/register")
	public User register() {
		logger.info("User.findAll()");
		return new User("", "");
	}
	
	@RequestMapping("/user/unregister")
	public User unRegister() {
		logger.info("User.findAll()");
		return new User("", "");
	}
	
}


