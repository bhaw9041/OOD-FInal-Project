package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.DatabaseConnector;

public class LoginController {

	@FXML
	private Button btnRegister;

	@FXML
	private Button btnSignIn;

	@FXML
	private TextField tfPassword;

	@FXML
	private TextField tfUsername;

	@FXML
	public void goToCatalog(ActionEvent event) {
		try {
			Connection conn = DatabaseConnector.getInstance();
			Statement st = conn.createStatement();
			String query = "select password from user_info where user_name = '" + tfUsername.getText() + "'";
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				String storedPassword = rs.getString(1);
				if (storedPassword.equals(tfPassword.getText())) {
					// Pop up login successful
					System.out.println("Login successful!");
					ScreenController.goToCatalogPage(event);
				} else {
					// Throw error saying Invalid Password.
					System.out.println("Incorrect Password");
				}
			} else {
				// Throw error saying Invalid UserName.
				System.out.println("Incorrect Username");
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void goToRegistration(ActionEvent event) {

		ScreenController.goToRegistrationPage(event);
	}

}
