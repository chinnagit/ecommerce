package com.ecommerce.gateway.controller;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ecommerce.gateway.dto.Foo;

@Controller
public class FooController {

    public FooController() {
        super();
    }
    
//    // API - read
//    @PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
//    @RequestMapping(method = RequestMethod.GET, value = "login")
//    @ResponseBody
//    public Response login(@PathVariable final String clientId) {
//    	final Map<String, String> params = new HashMap<String, String>();
//        params.put("response_type", "code");
//        params.put("client_id", clientId);
//        params.put("scope", "read,write");
//        final Response response = RestAssured.given().auth().preemptive().basic(clientId, "secret").and().with().params(params).when().post("http://localhost:9081/security-oauth-server/oauth/authorize");
//        return response;
//    }


    // API - read
    @PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/foos/{id}")
    @ResponseBody
    public Foo findById(@PathVariable final long id) {
        return new Foo(Long.parseLong(randomNumeric(2)), randomAlphabetic(4));
    }

    // API - write
    @PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('write')")
    @RequestMapping(method = RequestMethod.POST, value = "/foos")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Foo create(@RequestBody final Foo foo) {
        foo.setId(Long.parseLong(randomNumeric(2)));
        return foo;
    }

}
