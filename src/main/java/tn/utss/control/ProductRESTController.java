package tn.utss.control;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.utss.model.Product;
import tn.utss.repository.ProductRepository;
import tn.utss.service.ProductService;
import tn.utss.service.ProductServiceImpl;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/utss/tn")
public class ProductRESTController {
	
	@Autowired
	ServletContext context;

	@Autowired
	ProductService serviceProduct;
	
	@Autowired 
	ProductRepository pr;

	@GetMapping("/AllProducts")
	public List<Product> getAllProducts() {
		List<Product> list = serviceProduct.retrieveAllProducts();
		return list;
	}

	@GetMapping("/OneProduct/{idProduct}")
	@ResponseBody
	public Product getOneProduct(@PathVariable("idProduct") long idProduct) {

		return serviceProduct.retrieveProduct(idProduct);

	}

	@PostMapping(value = "/addProduct/{idStock}/{idSubcategory}")
	public Product addProduct(@RequestBody Product Product,@PathVariable("idStock")long idStock,@PathVariable ("idSubcategory")long idSubcategory ) {
		serviceProduct.addProduct(Product,idStock,idSubcategory);
		return Product;

	}

	@PutMapping(value = "/updateProduct/{idStock}")
	public Product updateOneProduct(@RequestBody Product Product,@PathVariable long idStock) {
		return serviceProduct.updateOneProduct(Product,idStock);

	}

	@DeleteMapping(value = "/deleteProduct/{idProduct}")
	public void deleteProduct(@PathVariable("idProduct") long idProduct) {
		serviceProduct.deleteProduct(idProduct);
	}
	
	
			@GetMapping(value="/Imgarticles/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
			 public byte[] getPhoto(@PathVariable("id") long id) throws Exception{
				 Product prod  = pr.findById(id).get();
				 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+prod.getFileName()));
			 }
	
}
