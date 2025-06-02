package com.Quizer.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Quizer.DTO.UserDto;
import com.Quizer.Entity.User;
import com.Quizer.Repository.UserRepository;
import com.Quizer.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	 private final UserRepository userRepository;
	 
	    @Autowired
	    private PasswordEncoder passwordEncoder;


	    public UserServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    // Convert Entity -> DTO
	    private UserDto convertToDto(User user) {
	        UserDto dto = new UserDto();
	        dto.setId(user.getId());
	        dto.setUsername(user.getUsername());
	        dto.setEmail(user.getEmail());
	        dto.setPassword(user.getPassword());
	        dto.setRole(user.getRole());
	        return dto;
	    }

	    // Convert DTO -> Entity
	    private User convertToEntity(UserDto dto) {
	        User user = new User();
	        user.setId(dto.getId());
	        user.setUsername(dto.getUsername());
	        user.setEmail(dto.getEmail());
	        user.setPassword(dto.getPassword());
	        user.setRole(dto.getRole());
	        return user;
	    }
	    
	    @Override
	    public UserDto createUser(UserDto userDto) {
	        User user = convertToEntity(userDto);
	        // Encode the password before saving
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        User saved = userRepository.save(user);
	        return convertToDto(saved);
	    }
	    
	    @Override
	    public List<UserDto> getAllUsers() {
	        return userRepository.findAll().stream()
	                .map(this::convertToDto)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public UserDto getUserById(Long id) {
	        User user = userRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
	        return convertToDto(user);
	    }

	    @Override
	    public UserDto updateUser(Long id, UserDto userDto) {
	        User existing = userRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

	        existing.setUsername(userDto.getUsername());
	        existing.setEmail(userDto.getEmail());

	        // Encode the new password if changed
	        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
	            existing.setPassword(passwordEncoder.encode(userDto.getPassword()));
	        }

	        User updated = userRepository.save(existing);
	        return convertToDto(updated);
	    }


//	    @Override
//	    public UserDto createUser(UserDto userDto) {
//	        User user = convertToEntity(userDto);
//	        User saved = userRepository.save(user);
//	        return convertToDto(saved);
//	    }
//
//	    @Override
//	    public List<UserDto> getAllUsers() {
//	        return userRepository.findAll().stream()
//	                .map(this::convertToDto)
//	                .collect(Collectors.toList());
//	    }
//
//	    @Override
//	    public UserDto getUserById(Long id) {
//	        User user = userRepository.findById(id)
//	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
//	        return convertToDto(user);
//	    }
//
//	    @Override
//	    public UserDto updateUser(Long id, UserDto userDto) {
//	        User existing = userRepository.findById(id)
//	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
//
//	        existing.setUsername(userDto.getUsername());
//	        existing.setEmail(userDto.getEmail());
//	        existing.setPass(userDto.getPass());
//
//	        User updated = userRepository.save(existing);
//	        return convertToDto(updated);
//	    }

	    @Override
	    public void deleteUser(Long id) {
	        userRepository.deleteById(id);
	    }
}
