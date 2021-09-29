package tn.utss.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import tn.utss.model.SubCategory;
import tn.utss.service.SubCategoryServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/utss/tn")
public class SubCategoryRESTController {

	@Autowired
	SubCategoryServiceImpl serviceSubCategory;

	@GetMapping("/AllSubCategorys")
	public List<SubCategory> getAllSubCategorys() {
		List<SubCategory> list = serviceSubCategory.retrieveAllSubCategories();
		return list;
	}

	@GetMapping("/OneSubCategory/{idSubCategory}")
	@ResponseBody
	public SubCategory getOneSubCategory(@PathVariable("idSubCategory") long idSubCategory) {

		return serviceSubCategory.retrieveSubCategory(idSubCategory);

	}

	@PostMapping(value = "/addSubCategory")
	public SubCategory addSubCategory(@RequestBody SubCategory SubCategory) {
		serviceSubCategory.addSubCategory(SubCategory);
		return SubCategory;

	}

	@PutMapping(value = "/updateSubCategory")
	public SubCategory updateSubCategory(@RequestBody SubCategory SubCategory) {
		return serviceSubCategory.updateSubCategory(SubCategory);

	}

	@DeleteMapping(value = "/deleteSubCategory/{idSubCategory}")
	public void deleteSubCategory(@PathVariable("idSubCategory") long idSubCategory) {
		serviceSubCategory.deleteSubCategory(idSubCategory);
	}
}
