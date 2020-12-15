package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

/*
 * Esta Classe é responsável por implementar os CRUDs das interfaces
 */

public class VendedorDaoJDBC implements VendedorDao {
	
	private Connection conn = null;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		
		PreparedStatement query = null;
		ResultSet result = null;
		try {
			query = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?"
					);
			
			query.setInt(1, id);
			
			result = query.executeQuery();
			
			if(result.next()) {
				// criando objeto departamento da tabela DB
				Departamento depto = new Departamento();
				depto.setId(result.getInt("DepartmentId"));
				depto.setName(result.getString("DepName"));
				
				// criando objeto vendedor da tabela DB
				Vendedor vend = new Vendedor();
				vend.setId(result.getInt("Id"));
				vend.setName(result.getString("Name"));
				vend.setEmail(result.getString("Email"));
				vend.setBaseSalary(result.getDouble("BaseSalary"));
				vend.setBirthDate(result.getDate("BirthDate").toLocalDate()); // converte data sql p/ LocalDate
				
				vend.setDepartment(depto); // criado com objeto Departamento (q foi criado c/ dados do DB)
				
				return vend;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.fecharSatement(query);
			DB.fecharResultSet(result);
		}
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
