package im.minyoung.exapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController {
	
	private Map<String, Product> products = new HashMap<String, Product>() {
		private static final long serialVersionUID = 1L;
		{
			put("1", new Product("1", "제품1", 2000));
			put("2", new Product("2", "제품2", 3000));
			put("3", new Product("3", "제품3", 4000));
		}
	};
	
	/* 전체검색 */
	@GetMapping
	public List<Product> getAll() {
		return new ArrayList<>(products.values());
	}
	
	/* 1건 검색 */
	@GetMapping("{id}")
	public Product getById(@PathVariable("id") String id) {
		return products.get(id);
	}
	
	/* 등록 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Product product) {
		products.put(product.getId(), product);
	}
	
	/* 업데이트 */
	@PutMapping
	public ResponseEntity<Object> update(Product product) {
		HttpStatus status = products.containsKey(product.getId()) ? HttpStatus.NO_CONTENT:HttpStatus.CREATED;
		products.put(product.getId(), product);
		return ResponseEntity.status(status).body(null);
	}
	
	/* 삭제 */
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		products.remove(id);
	}
}
