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
		Vendedor vend = vendDao.findById(7); // criado com dados do sql
		System.out.println(vend);

		System.out.println("\n====| TESTE 2: Vendedor findByDepartment (2) |====");
		Departamento departamento = new Departamento(2, null); // buscar apenas o id
		List<Vendedor> vendedores = vendDao.findByDepartment(departamento); // chama o findByDepartment
		for (Vendedor vendedor : vendedores) { // varre a lista
			System.out.println(vendedor);
		}
		
		System.out.println("\n====| TESTE 3: Vendedor findAll |====");
		vendedores = vendDao.findAll(); // chama o findByDepartment
		for (Vendedor vendedor : vendedores) { // varre a lista
			System.out.println(vendedor);
		}
		
		System.out.println("\n====| TESTE 4: Vendedor insert |====");
		// passando dados p/ o construtor, depois p/ o sql
		/*Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@mail.com", "18/10/1980", 4000.0, departamento);
		vendDao.insert(novoVendedor);
		System.out.println("Inserido! Novo ID: " + novoVendedor.getId());*/
		
		System.out.println("\n====| TESTE 5: Vendedor update |====");
		/*vend = vendDao.findById(1);
		vend.setName("Martha Waine");
		vendDao.update(vend);
		System.out.println("Update concluído com sucesso!");*/
		
		System.out.println("\n====| TESTE 6: Vendedor delete |====");
		vendDao.deleteById(12);
	}

}
