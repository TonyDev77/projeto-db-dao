package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendDao = DaoFactory.criaVendedorDao();
		
		
		System.out.println("====| TESTE 1: Vendedor findById |====");
		Vendedor vend = vendDao.findById(7);
		System.out.println(vend);

		System.out.println("====| TESTE 2: Vendedor findByDepartment |====");
		Departamento departemento = new Departamento(2, null); // buscar apenas o id
		List<Vendedor> vendedores = vendDao.findByDepartment(departemento); // chama o findByDepartment
		for (Vendedor vendedor : vendedores) { // varre a lista
			System.out.println(vendedor);
		}
	}

}
