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

import tn.utss.model.Movement;
import tn.utss.service.MovementServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/utss/tn")
public class MovementRESTController {

	@Autowired
	MovementServiceImpl serviceMovement;

	@GetMapping("/AllMovements")
	public List<Movement> getAllMovements() {
		List<Movement> list = serviceMovement.retrieveAllMovements();
		return list;
	}

	@GetMapping("/OneMovement/{idMovement}")
	@ResponseBody
	public Movement getOneMovement(@PathVariable("idMovement") long idMovement) {

		return serviceMovement.retrieveMovement(idMovement);

	}

	@PostMapping(value = "/addMovement")
	public Movement addMovement(@RequestBody Movement Movement) {
		serviceMovement.addMovement(Movement);
		return Movement;

	}

	@PutMapping(value = "/updateMovement")
	public Movement updateMovement(@RequestBody Movement Movement) {
		return serviceMovement.updateMovement(Movement);

	}

	@DeleteMapping(value = "/deleteMovement/{idMovement}")
	public void deleteMovement(@PathVariable("idMovement") long idMovement) {
		serviceMovement.deleteMovement(idMovement);
	}
	
	@PostMapping(value="/supplyReception/{idStock}")
	public Movement SupplyReception(@RequestBody Movement movement ,
									
									@PathVariable("idStock") long idStock) {
		
			 
		return serviceMovement.supplyReception(movement ,idStock);
	}

	@PostMapping(value="/inetrnalProduction/{idStock}")
	public Movement inetrnalProduction(@RequestBody Movement movement ,
									
									@PathVariable("idStock") long idStock) {
		
			 
		return serviceMovement.internalProduction(movement ,idStock);
	}
	@PutMapping(value="/destruction/{idStock}")
	public Movement destruction(@RequestBody Movement movement ,
									
									@PathVariable("idStock") long idStock) {
		
			 
		return serviceMovement.desTruction(movement ,idStock);
	}
	
	@PutMapping(value="/toOtherStock/{idStock1}/{idStock2}")
	public Movement toOtherStock(@RequestBody Movement movement ,
									
									@PathVariable("idStock1") long idStock1,@PathVariable("idStock2") long idStock2) {
		
			 
		return serviceMovement.toOtherStore(movement, idStock1, idStock2);
	}
	
	
	}
