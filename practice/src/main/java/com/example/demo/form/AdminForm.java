package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;


@Data
public class AdminForm implements Serializable {
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 8, max = 14, message = "パスワードは8文字以上14文字以下にしてください")
	private String password;

}
