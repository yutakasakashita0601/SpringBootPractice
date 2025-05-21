package com.example.demo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "contacts")
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotBlank(message = "姓を入力してください")
	@Column(name = "last_name", nullable =false)
	private String lastName;
	
	@NotBlank(message = "名を入力してください")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が正しくありません")
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotBlank(message = "電話番号を入力してください")
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@NotBlank(message = "郵便番号を入力してください")
	@Column(name = "zip_code", nullable = false)
	private String zipCode;
	
	@NotBlank(message = "住所を入力してください")
	@Column(name = "address", nullable = false)
	private String address;
	
	@NotBlank(message = "建物名を入力してください")
	@Column(name = "building_name", nullable = false)
	private String buildingName;
	
	@NotBlank(message = "お問い合わせ種別を入力してください")
	@Column(name = "contact_type", nullable = false)
	private String contactType;
	
	@NotBlank(message = "お問い合わせ内容を入力してください")
	@Column(name = "body", nullable = false)
	private String body;
	
	@Column(name = "created_at" , nullable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

}
