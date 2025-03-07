package model.repositories;

public interface BasicCrud {

		Object create(Object object);
		
		Object findById(Long id);
		
		Object updateById(Object object);
		
		void delete(Object object);
	
}
