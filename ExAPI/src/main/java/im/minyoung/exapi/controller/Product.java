package im.minyoung.exapi.controller;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Product {
	

	@NotBlank
	private String id;
	
	@NotBlank
	private String name;
	
	private Integer price;

	public Product(String id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
}
