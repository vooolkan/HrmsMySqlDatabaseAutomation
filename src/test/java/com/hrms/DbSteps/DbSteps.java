package com.hrms.DbSteps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DbSteps {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	@Given("I create connection with SyntaxHrms database")
	public void i_create_connection_with_SyntaxHrms_database() {

		String dbURL = "jdbc:mysql://166.62.36.207:3306/syntaxhrm_mysql";
		String userName = "syntax_hrm";
		String password = "syntaxhrm123";

		try {
			connection = DriverManager.getConnection(dbURL, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Given("I create a statement")
	public void i_create_a_statement() {

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@When("I retrieve all job tittles from database")
	public void i_retrieve_all_job_tittles_from_database() {
		

		String query = "SELECT DISTINCT(job_title) FROM ohrm_job_title";

		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("I validate results for Job Titles")
	public void i_validate_results_for_Job_Titles(io.cucumber.datatable.DataTable dataTable) throws SQLException {

		// we will compare results in resulSet and in dataTabe
		
		List<Map<String, String>> expResults=dataTable.asMaps();
		
		Iterator <Map<String, String>> it=expResults.iterator();
		
//		resultSet.next();
		
		while(it.hasNext()) {
			
			// retrieve expected value from datatable
			Map<String, String> expRes=it.next();
			String expJobTitle=expRes.get("job_title");
			
			// retrive actual result from resultSet
			
			resultSet.next();
			String actJobTitle=resultSet.getString("job_title");
			
			
			Assert.assertEquals(expJobTitle, actJobTitle);
			
		}
		
		
	}

	@Then("I close all connections")
	public void i_close_all_connections() throws SQLException {

		resultSet.close();
		statement.close();
		connection.close();
	}
}
