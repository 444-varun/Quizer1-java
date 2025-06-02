package com.Quizer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Quizer.DTO.UserDto;
import com.Quizer.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	    private final UserService userService;
	  
	  // Constructor Injection (Recommended)
	    public UserController(UserService userService) {
	        this.userService = userService;
	    }

	    // Create/Register a new user
	    @PostMapping("/register")
	    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
	    	  System.out.println("Incoming user DTO: " + userDto);
	        UserDto createdUserDto = userService.createUser(userDto);
	        return ResponseEntity.ok(createdUserDto);
	    }

	 // Get all users
	    @GetMapping("/")
	    public ResponseEntity<List<UserDto>> getAllUsers() {
	        List<UserDto> users = userService.getAllUsers();
	        return ResponseEntity.ok(users);
	    }

	    // Get user by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
	        UserDto user = userService.getUserById(id);
	        return ResponseEntity.ok(user);
	    }

	    // Update user
	    @PutMapping("/{id}")
	    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
	        UserDto updatedUser = userService.updateUser(id, userDto);
	        return ResponseEntity.ok(updatedUser);
	    }

	    // Delete user
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	        return ResponseEntity.ok("User deleted successfully.");
	    }
}
