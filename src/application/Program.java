package application;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendDao = DaoFactory.criaVendedorDao();
		
		
		System.out.println("====| TESTE 1: Vendedor findById |====");
		Vendedor vend = vendDao.findById(7);
		
		System.out.println(vend);

	}

}
