package controller;

import model.entities.Pets;

import model.services.PetsService;

import java.util.List;

public class PetsController {
	
private PetsService petsService = new PetsService();
	
	public Pets create(Object object) {
		
		return petsService.create(petsService);
	}
	public Pets updateById(Pets pets) {
		
		return petsService.update(pets);
	}
	public void delete(Pets pets) {
		if(petsService.delete(pets) != null) {
			System.out.println("Deletado");
		}else
			System.out.println("Não foi encontrado");
	}
	
	public Pets findById(Long id) {
		
		return petsService.findById(id);
	}
	public List<Pets>findall(){
		
		return petsService.findAll();
	}
    
}
