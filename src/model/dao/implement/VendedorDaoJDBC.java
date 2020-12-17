package model.dao.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void insert(Vendedor vendedor) {
		
		PreparedStatement query = null;
		
		try {
			query = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS
				);
			query.setString(1, vendedor.getName());
			query.setString(2, vendedor.getEmail());
			query.setDate(3, Date.valueOf(vendedor.getBirthDate())); // Date aqui é do java.sql.date
			query.setDouble(4, vendedor.getBaseSalary());
			query.setInt(5, vendedor.getDepartment().getId());
			
			int linhasAfetadas = query.executeUpdate();
			
			if (linhasAfetadas > 0) {
				ResultSet result = query.getGeneratedKeys();
				if (result.next()) {
					int id = result.getInt(1); // pega o ID gerado na inserção 
					vendedor.setId(id); // seta o id do objeto vendedor c/ o id retornado do sql
				}
				
				DB.fecharResultSet(result);
				
			} else {
				throw new DbException("Erro inesperado: Nenhuma linha foi alterada!");
			}
			
		} catch (SQLException e) {
			throw new  DbException(e.getMessage());
			
		} finally {
			DB.fecharSatement(query);
		}
	}

	@Override
	public void update(Vendedor vendedor) {
		
		PreparedStatement query = null;
		
		try {
			query = conn.prepareStatement(
					"UPDATE seller "
					+ "SET Name = ?, "
					+ "Email = ?, "
					+ "BirthDate = ?, "
					+ "BaseSalary = ?, "
					+ "DepartmentId = ? "
					+ "WHERE Id = ?" 
				);
			query.setString(1, vendedor.getName());
			query.setString(2, vendedor.getEmail());
			query.setDate(3, Date.valueOf(vendedor.getBirthDate())); // O "Date" aqui, é do java.sql.date
			query.setDouble(4, vendedor.getBaseSalary());
			query.setInt(5, vendedor.getDepartment().getId());
			query.setInt(6, vendedor.getId());
			
			query.executeUpdate();
			
		} catch (SQLException e) {
			throw new  DbException(e.getMessage());
			
		} finally {
			DB.fecharSatement(query);
		}
		
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
				Departamento depto = criaDepartamento(result);
				// criando objeto vendedor da tabela DB
				Vendedor vend = criaVendedor(result, depto);
				
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

	
	// Cria objeto departamento a partir do SQL
	private Departamento criaDepartamento(ResultSet result) throws SQLException {
		Departamento depto = new Departamento();
		depto.setId(result.getInt("DepartmentId"));
		depto.setName(result.getString("DepName"));
		
		return depto;
	}
	
	// Cria objeto vendedor a partir do SQL
	private Vendedor criaVendedor(ResultSet result, Departamento depto) throws SQLException {
		Vendedor vend = new Vendedor();
		vend.setId(result.getInt("Id"));
		vend.setName(result.getString("Name"));
		vend.setEmail(result.getString("Email"));
		vend.setBaseSalary(result.getDouble("BaseSalary"));
		vend.setBirthDate(result.getDate("BirthDate").toLocalDate()); // converte data sql p/ LocalDate
		
		vend.setDepartment(depto); // criado com objeto Departamento (q foi criado c/ dados do DB)
		
		return vend;
	}

	@Override
	public List<Vendedor> findAll() {
		PreparedStatement query = null;
		ResultSet result = null;
		
		try {
			query = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name"
				);
			result = query.executeQuery();
			
			List<Vendedor> listVend = new ArrayList<>(); // lista p/ guardar o resultado
			
			/*
			 * O Map e o if abaixo servem para evitar que a
			 * cada passada do laço seja instanciado o mesmo departamento mais de uma vez, pois ele não 
			 * admite repetições, mantendo assim um único id/departamento por tipo, dentro do Map na memória.
			 */
			Map<Integer, Departamento> map = new HashMap<>(); // Map vazio p/ não repetir departamento
			
			while (result.next()) {
				Departamento depto = map.get(result.getInt("DepartmentId")); // se não existir no map, retorna null
				
				if (depto == null) { // testa se departamento é null
					depto = criaDepartamento(result); // instancia departamento
					map.put(result.getInt("DepartmentId"), depto); // guarda no map
				}
				
				Vendedor vend = criaVendedor(result, depto);
				listVend.add(vend);
			}
			
			return listVend;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.fecharSatement(query);
			DB.fecharResultSet(result);
		}
	}

	@Override
	public List<Vendedor> findByDepartment(Departamento departamento) {
		
		PreparedStatement query = null;
		ResultSet result = null;
		
		try {
			query = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name"
				);
			query.setInt(1, departamento.getId()); // atribui valor p/ o "?"
			result = query.executeQuery();
			
			List<Vendedor> listVend = new ArrayList<>(); // lista p/ guardar o resultado
			
			/*
			 * O Map e o if abaixo servem para que todos os vendedores apontem apenas
			 * para um departamento (UM departamento tem MUITOS vendedores). O map irá evitar que a
			 * cada passada do laço seja instanciado um novo departamento, pois ele não admite repetições,
			 * mantendo assim um único id/departamento dentro do Map na memória.
			 */
			Map<Integer, Departamento> map = new HashMap<>(); // Map vazio p/ não repetir departamento
			
			while (result.next()) {
				Departamento depto = map.get(result.getInt("DepartmentId")); // se não existir no map, retorna null
				
				if (depto == null) { // testa se departamento é null
					depto = criaDepartamento(result); // instancia departamento
					map.put(result.getInt("DepartmentId"), depto); // guarda no map
				}
				
				Vendedor vend = criaVendedor(result, depto);
				listVend.add(vend);
			}
			
			return listVend;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.fecharSatement(query);
			DB.fecharResultSet(result);
		}
	}

}
