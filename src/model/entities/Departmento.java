package model.entities;

import java.io.Serializable;

public class Departmento implements Serializable {

	// versão padrão do serialize obrigatoriamente implementada
	private static final long serialVersionUID = 1L;
	
	// atributos
	private int id = 0;
	private String name = "";
	
	// construtores
	public Departmento() {
		
	}
	
	public Departmento(int id, String name) {
		this.id = id;
		this.name = name;
	}

	// getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departmento other = (Departmento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Departmento [id: " + id + ", nome: " + name + "]";
	}
}
