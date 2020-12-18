package model.dao;

import db.DB;
import model.dao.implement.VendedorDaoJDBC;
import model.dao.implement.DepartamentoDaoJDBC;

/*
 * Esta Classe é responsável por instanciar as DAOs que implementaram as interfaces
 */

public class DaoFactory {
	
	public static VendedorDao criaVendedorDao () {
		return new VendedorDaoJDBC(DB.getConexao());
	}
	
	public static DepartamentoDao criaDepartamentoDao () {
		return new DepartamentoDaoJDBC(DB.getConexao());
	}
}
