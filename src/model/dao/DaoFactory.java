package model.dao;

import model.dao.implement.VendedorDaoJDBC;

/*
 * Esta Classe é responsável por instanciar as as DAOs que implementaram as inferfaces
 */

public class DaoFactory {
	
	public static VendedorDao criaVendedorDao () {
		return new VendedorDaoJDBC();
	}
}
