package model.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Vendedor  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id = 0;
	private String name = "";
	private String email = "";
	private LocalDate birthDate = null;
	private Double baseSalary = 0.00;
	private Departamento department = null;
	
	public Vendedor () {
		
	}

	public Vendedor(Integer id, String name, String email, LocalDate birthDate, Double baseSalary, Departamento department) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Departamento getDepartment() {
		return department;
	}

	public void setDepartment(Departamento department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Vendedor other = (Vendedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vendedor [id: " + id + ", name: " + name + ", email: " + email + ", birthDate: " + birthDate
				+ ", baseSalary: " + baseSalary + ", department: " + department + "]";
	}
	
	
	
}
