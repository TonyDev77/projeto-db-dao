package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {
	
	private Connection conn = null;

	// construtor
	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Departamento departamento) {
		
		PreparedStatement query = null;
		
		try {
			query = conn.prepareStatement(
					"INSERT INTO department "
					+ "(Name) "
					+ "VALUES (?)", Statement.RETURN_GENERATED_KEYS
				);
			
			query.setString(1, departamento.getName()); // sta o place holder
			
			int linhasAfetadas = query.executeUpdate();
			
			if (linhasAfetadas > 0) {
				ResultSet result = query.getGeneratedKeys();
				
				if (result.next()) {
					int id = result.getInt(1); // pega índice 1 da tabela retornada com os dados
					departamento.setId(id);
				}
				
				DB.fecharResultSet(result);
				
			} else {
				throw new DbException("Erro inesperado: Nenhuma linha alterada!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.fecharSatement(query);
		}

	}

	@Override
	public void update(Departamento departamento) {
		
		PreparedStatement query = null;
		
		try {
			query = conn.prepareStatement(
					"UPDATE department "
					+ "SET Name = ? "
					+ "WHERE Id = ?" 
				);
			query.setString(1, departamento.getName());
			query.setInt(2, departamento.getId());
			
			
			query.executeUpdate();
			
		} catch (SQLException e) {
			throw new  DbException(e.getMessage());
			
		} finally {
			DB.fecharSatement(query);
		}

	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement query = null;
		
		try {
			query = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE Id = ?"
				);
			
			query.setInt(1, id);
			
			int linhasAfetadas = query.executeUpdate();
			
			if (linhasAfetadas == 0) {
				System.out.println("O id não existe no BD");;
			} else {
				System.out.println("O id: " + id + " foi deletado com sucesso!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.fecharSatement(query);
		}

	}
	
	@Override
	public Departamento findById(Integer id) {
		
		PreparedStatement query = null;
		ResultSet result = null;
		
		try {
			query = conn.prepareStatement(
					"SELECT * FROM department  "
					+ "WHERE id = ?"
					);
			
			query.setInt(1, id);
			
			result = query.executeQuery();
			
			if(result.next()) {
				// criando objeto departamento da tabela DB
				Departamento depto = new Departamento();
				depto.setId(result.getInt("Id"));
				depto.setName(result.getString("Name"));
				
				return depto;
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
	public List<Departamento> findAll() {
		
		PreparedStatement query = null;
		ResultSet result = null;
		
		try {
			query = conn.prepareStatement(
					"SELECT * "
					+ "FROM department"
				);
			
			result = query.executeQuery();
			
			List<Departamento> listDepto = new ArrayList<>(); // lista p/ guardar o resultado
			
			while (result.next()) {
				Departamento depto = new Departamento();
				depto.setId(result.getInt("Id"));
				depto.setName(result.getString("Name"));
				
				listDepto.add(depto);
			}
			
			return listDepto;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.fecharSatement(query);
			DB.fecharResultSet(result);
		}
	}

}
