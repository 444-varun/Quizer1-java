package com.Quizer.DTO;

import com.Quizer.Entity.Role;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {

	  private Long id;
	    private String username;
	    private String email;
	    @Column(name = "password")
	    private String password;
	    private Role role;
}
