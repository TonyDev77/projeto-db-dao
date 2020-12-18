package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class Program2 {

	public static void main(String[] args) {
		
		DepartamentoDao depDao = DaoFactory.criaDepartamentoDao();
		Departamento depart = null;
		
		System.out.println("\n====| TESTE 1: Departamento insert |====");
		// passando dados p/ o construtor, depois p/ o sql
		/*Departamento novoDepart = new Departamento(null, "Music");
		depDao.insert(novoDepart);
		System.out.println("Inserido! Novo ID: " + novoDepart.getId());*/
		
		System.out.println("\n====| TESTE 2: Departamento update |====");
		/*depart = depDao.findById(6);
		depart.setName("Eletros");
		depDao.update(depart);
		System.out.println("Update concluído com sucesso!");*/
		
		System.out.println("====| TESTE 3: Departamento findById |====");
		depart = depDao.findById(6); // criado com dados do sql
		System.out.println(depart);
		
		System.out.println("\n====| TESTE 4: Departamento findAll |====");
		List<Departamento> departamentos = depDao.findAll();
		for (Departamento departamento : departamentos) { // varre a lista
			System.out.println(departamento);
		}
		
		System.out.println("\n====| TESTE 5: Departamento deleteById |====");
		depDao.deleteById(6);

	}

}
