package application;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = DaoFactory.criaVendedorDao();
		
		Vendedor vend = vendedorDao.findById(7);
		
		System.out.println(vend);

	}

}
