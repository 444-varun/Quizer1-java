package com.Quizer.Service;

import java.util.List;

import com.Quizer.DTO.UserDto;

public interface UserService {

	 UserDto createUser(UserDto userDto);
	    List<UserDto> getAllUsers();
	    UserDto getUserById(Long id);
	    UserDto updateUser(Long id, UserDto userDto);
	    void deleteUser(Long id);
	    
	
}
