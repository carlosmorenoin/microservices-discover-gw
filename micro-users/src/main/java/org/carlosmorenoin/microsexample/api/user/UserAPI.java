package org.carlosmorenoin.microsexample.api.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.carlosmorenoin.microsexample.core.User;
import org.carlosmorenoin.microsexample.core.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class UserAPI {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> addUser(@RequestBody User user) {
        final User addedUser = userService.addUser(user);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping("/users/{id}")
    @ResponseBody
    public User retrieveUser(@PathVariable("id") final String id) {
        return userService.retrieveUser(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> retrieveUsers() {
        return userService.retrieveUsers();
    }
}
