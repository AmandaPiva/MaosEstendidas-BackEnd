package com.tcc.maosestendidas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class MaosestendidasApplication {

	public static void main(String[] args) {
//		try {
//			// Substitua pela sua URL de conexão, nome de usuário e senha do ElephantSQL
//			String url = "jdbc:postgresql://spoxkqrz:XBLIKMksG9mHL-uDDlOUP0jCvDWyyhBF@kesavan.db.elephantsql.com/spoxkqrz";
//			String user = "spoxkqrz";
//			String password = "XBLIKMksG9mHL-uDDlOUP0jCvDWyyhBF";
//
//			Connection connection = DriverManager.getConnection(url, user, password);
//			if (connection != null) {
//				System.out.println("Conectado ao banco de dados!");
//				// Faça algo com a conexão
//			}
//			connection.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		SpringApplication.run(MaosestendidasApplication.class, args);
	}

}
