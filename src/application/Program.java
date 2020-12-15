package application;

import java.time.LocalDate;

import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Livros");
		System.out.println(obj);
		
		Vendedor vendedor = new Vendedor(1, "Boby", "boby@mail.com", LocalDate.now(), 3000.00, obj);
		System.out.println(vendedor);

	}

}
