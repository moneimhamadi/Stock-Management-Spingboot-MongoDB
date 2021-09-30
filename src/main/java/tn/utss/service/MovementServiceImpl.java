package tn.utss.service;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Service;

import tn.utss.model.Movement;
import tn.utss.model.MovementType;
import tn.utss.model.Product;

import tn.utss.repository.MovementRepository;
import tn.utss.repository.ProductRepository;
import tn.utss.repository.StockRepository;
import tn.utss.repository.StoreRepository;

@Service
public class MovementServiceImpl implements MovementService {

	@Autowired
	MovementRepository movementRepository;
	
	@Autowired
	MovementService movementService;
	
	@Autowired
	ProductService ProductService;
	
	@Autowired
	ProductRepository ProductReporsitory;
	
	@Autowired
	StoreRepository StoreReporsitory;
	
	@Autowired
	StockRepository stockReporsitory;
	
	@Autowired
	StockService stockservice;
	
	List<Product> pr =new ArrayList<>();
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	private static final Logger L = LogManager.getLogger(MovementServiceImpl.class);

	
	LocalDate localDate = LocalDate.now();
	Date date = new Date();
	
	@Override
	public List<Movement> retrieveAllMovements() {
		return movementRepository.findAll();
	}

	@Override
	public Movement retrieveMovement(long idMovement) {
		Movement Movement = movementRepository.findById(idMovement).get();
		L.info("Movement returned +++ :" + Movement);
		return Movement;
	}

	@Override
	public Movement addMovement(Movement m) {
	       

		m.setIdMovement(sequenceGeneratorService.generateSequence(Movement.SEQUENCE_NAME));
		List <Product> mvprods=new ArrayList<Product>();
		mvprods=m.getMovProducts();
		m.setOrderDate(date);
		for (Product p :mvprods){
			p.setIdProduct(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));
		}
		ProductReporsitory.saveAll(m.getMovProducts());
		m.setMovProducts(m.getMovProducts());
	
		return movementRepository.save(m);
	}

	@Override
	public Movement updateMovement(Movement c) {
		return movementRepository.save(c);
	}

	@Override
	public void deleteMovement(long idMovement) {
		movementRepository.deleteById(idMovement);

	}

	
	@Override
	public Movement supplyReception(Movement mov,long idStock) {
		Date date = new Date();
		List<Product> StkProducts=new ArrayList<Product>();
		List <Product> MovProducts=new ArrayList<Product>();
		
		mov.setIdMovement(sequenceGeneratorService.generateSequence(Movement.SEQUENCE_NAME));
		mov.setMovType(MovementType.SUPPLYRECEPTION);
		mov.setOrderDate(date);
			
		movementRepository.save(mov);
		
		 MovProducts=movementRepository.findById(mov.getIdMovement()).get().getMovProducts();
		StkProducts=stockReporsitory.findById(idStock).get().getStockProducts();
		System.out.println(StkProducts.toString());
		for(int i = 0; i < MovProducts.size(); i++) {
			System.out.println(MovProducts.get(i).getBarcode().toString());
		    for (int j = 0; j < StkProducts.size(); j++) {
		    	
		        if (MovProducts.get(i).getBarcode().toString().equals(StkProducts.get(j).getBarcode().toString())){
		        
		        	StkProducts.get(j).setQuantityProduct(StkProducts.get(j).getQuantityProduct()+MovProducts.get(i).getQuantityProduct());
 		        		        
		        ProductService.updateOneProduct(StkProducts.get(j), idStock);
		               
		        }
//		         else {
//		        	 ProductService.addProduct(MovProducts.get(i), idStock);
//		         }   
		    }
		    
		}
		
		return mov;
		}

	@Override
	public Movement internalProduction(Movement mov, long idStock) {
		
		
		List<Product> StkProducts=new ArrayList<Product>();
		List <Product> MovProducts=new ArrayList<Product>();
		
		mov.setIdMovement(sequenceGeneratorService.generateSequence(Movement.SEQUENCE_NAME));
		mov.setMovType(MovementType.INTERNALPRODUCTION);
		mov.setOrderDate(date);
		movementRepository.save(mov);
		
		 MovProducts=movementRepository.findById(mov.getIdMovement()).get().getMovProducts();
		StkProducts=stockReporsitory.findById(idStock).get().getStockProducts();
		
		for(int i = 0; i < MovProducts.size(); i++) {								
		    for (int j = 0; j < StkProducts.size(); j++) {
		        if (MovProducts.get(i).getBarcode().toString().equals(StkProducts.get(j).getBarcode().toString())){
		        	StkProducts.get(j).setQuantityProduct(StkProducts.get(j).getQuantityProduct()+MovProducts.get(i).getQuantityProduct());
 		        		        
		        ProductService.updateOneProduct(StkProducts.get(j), idStock);
		               
		        }
//		         else {
//		        	 ProductService.addProduct(MovProducts.get(i), idStock);
//		         }   
		    }
		    
		}
		
		return mov;
	}

	@Override
	public Movement desTruction(Movement mov, long idStock) {
		List<Product> StkProducts=new ArrayList<Product>();
		List <Product> MovProducts=new ArrayList<Product>();
		
		mov.setIdMovement(sequenceGeneratorService.generateSequence(Movement.SEQUENCE_NAME));
		mov.setMovType(MovementType.DESTRUCTION);
		mov.setOrderDate(date);
		movementRepository.save(mov);
		
		 MovProducts=movementRepository.findById(mov.getIdMovement()).get().getMovProducts();
		StkProducts=stockReporsitory.findById(idStock).get().getStockProducts();
		
		for(int i = 0; i < MovProducts.size(); i++) {								
		    for (int j = 0; j < StkProducts.size(); j++) {
		        if (MovProducts.get(i).getBarcode().toString().equals(StkProducts.get(j).getBarcode().toString())){
		        	StkProducts.get(j).setQuantityProduct(0);
 		        		        
		        ProductService.updateOneProduct(StkProducts.get(j), idStock);      
		        }
		    }   
		}
		return mov;
	}

	@Override
	public Movement toOtherStore(Movement mov, long IdStock1, long idStock2) {
		List<Product> SenderStock=new ArrayList<Product>();
		List<Product> RecieverStock=new ArrayList<Product>();
		List <Product> MovProducts=new ArrayList<Product>();
		
		mov.setIdMovement(sequenceGeneratorService.generateSequence(Movement.SEQUENCE_NAME));
		mov.setMovType(MovementType.TOSTORE);
		mov.setMovType(MovementType.TOSTORE);
			
		movementRepository.save(mov);
		
		 MovProducts=movementRepository.findById(mov.getIdMovement()).get().getMovProducts();
		
		 SenderStock=stockReporsitory.findById(IdStock1).get().getStockProducts();
		 RecieverStock=stockReporsitory.findById(idStock2).get().getStockProducts();
		
		for(int i = 0; i < MovProducts.size(); i++) {								
		    for (int j = 0; j < SenderStock.size(); j++) {
		        if (MovProducts.get(i).getBarcode().toString().equals(SenderStock.get(j).getBarcode().toString())){
		        	SenderStock.get(j).setQuantityProduct(SenderStock.get(j).getQuantityProduct()-MovProducts.get(i).getQuantityProduct());
	        
		        ProductService.updateOneProduct(SenderStock.get(j), IdStock1);       
		        }	
		    }    
		}
		for(int i = 0; i < MovProducts.size(); i++) {								
		    for (int j = 0; j < RecieverStock.size(); j++) {
		        if (MovProducts.get(i).getBarcode().toString().equals(RecieverStock.get(j).getBarcode().toString())){
		        	RecieverStock.get(j).setQuantityProduct(RecieverStock.get(j).getQuantityProduct()+MovProducts.get(i).getQuantityProduct());
	        
		        ProductService.updateOneProduct(RecieverStock.get(j), idStock2);       
		        }	
		    }
		    
		}
	
		return mov;
	}
	







}
	
	



//for(Product pMOV : MovProducts)
//{
//	ProductService.addProduct(pMOV, idStock);
//	for (Product pstk: StkProducts){
//		boolean c=pMOV.getBarcode().equals(pstk.getBarcode());
//		
//		if (pMOV.getBarcode()==(pstk.getBarcode())){
//			pstk.setQuantityProduct(pstk.getQuantityProduct()+pMOV.getQuantityProduct());
//			ProductService.updateProduct(pstk, idStock);
//			
//			
//	}
//		else ProductService.addProduct(pMOV, idStock);
//	}
//		
//}

	


//		List<Product> ExistingProducts=new ArrayList<Product>();
//		List <Product> NonExistProducts=new ArrayList<Product>(); 
//		
//		for (Product pMOV:MovProducts){
//			for (Product pSTK :StkProducts){
//			boolean c=pMOV.getBarcode().equals(pSTK.getBarcode());
//			if(c == true ){
//				
//				ExistingProducts.add(pMOV);
//				pSTK.setQuantityProduct(pSTK.getQuantityProduct()+pMOV.getQuantityProduct());
//				ProductReporsitory.saveAll(ExistingProducts);
//				movementRepository.save(mov);
//				}
//			
//			else {
//				for(Product p : MovProducts)
//					ProductService.addProduct(p, idStock);
//				for (Product nonExisting:MovProducts)
//				NonExistProducts.add(nonExisting);
				
//				ProductService.addProduct(nonExisting, idStock);
//				ProductReporsitory.saveAll(ExistingProducts);
				
//				movementRepository.save(mov);
//			}
//			}
//		}
		
	
/*MovProducts.forEach(prod ->prod.)
 
 * */






