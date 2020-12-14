package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConexao() {
		if (conn == null) {
			try {
				Properties props = getPropriedadesConexao();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void fecharConexao() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// Carrega os dados de conexão do arquivo "db.prperties"
	private static Properties getPropriedadesConexao() {
		
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs); // lê e guarda os dados do arquivo
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	// Ajuda a fechar as conexões sem usar try/catch
	public static void fecharSatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void fecharResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
