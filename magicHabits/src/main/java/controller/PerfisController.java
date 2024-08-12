package controller;

import java.util.List;

import model.entities.Perfis;
import model.services.PerfisService;



public class PerfisController {

private PerfisService perfisService = new PerfisService();

public Perfis create(Object object) {
	
	return perfisService.create(perfisService);
}
public Perfis updateById(Perfis perfis) {
	
	return perfisService.update(perfis);
}
public void delete(Perfis perfis) {
	if(perfisService.delete(perfis) != null) {
		System.out.println("Deletado");
	}else
		System.out.println("Não foi encontrado");
}

public Perfis findById(Long id) {
	
	return perfisService.findById(id);
}
public List<Perfis>findall(){
	
	return perfisService.findAll();
}
}
