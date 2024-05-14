package com.example.demo.userController;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.demo.userModel.DataModel;

public class ContactManager {
	String query = "";

	public static void main(String[] args) throws Exception {
		System.out.println("!!!");
		initialize();
	}

	public static void initialize() throws Exception{
		Scanner options = new Scanner(System.in);
		System.out.println(
				"What you what to do?\nEnter '1' to View all contant\nEnter '2' filter by name\nEnter 3 to Add new contact\nEnter 4 to Edit contact\nEnter 5 to Delete contact");
		int userRequest = options.nextInt();

		if(userRequest == 1){
			getAllData();
		}
		else if(userRequest == 2){
			getFilteredData();
		}
		else if(userRequest == 3){
			addData();
		}
		else if(userRequest == 4){
			updateData();
		}
		else if(userRequest == 5){
			removeContact();
		}
		else{

		}

	}

	public static Connection makeConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "dbc:oracle:thin:@192.168.1.139:1521:orcl";
		Connection con = DriverManager.getConnection(url, "DEMODB", "DEMODB");
		return con;
	}

	public static void terminateConnection(Connection con) throws Exception {
		con.close();
	}

	public static void executer(String query, int status) throws Exception {
		Connection connection = makeConnection();
		ArrayList<DataModel> dataSet = new ArrayList<>();

		Statement cursor = connection.createStatement();
		if (status == 1) {
			ResultSet result = cursor.executeQuery(query);
			try {
				while (result.next()) {
					DataModel data = new DataModel();
					data.setNAME(result.getString("NAME"));
					data.setPHONE(result.getString("PHONE"));
					data.setEMAIL(result.getString("EMAIL"));
					dataSet.add(data);
				}
				for (DataModel i : dataSet) {
					System.out.println(i);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			try {
				cursor.executeQuery(query);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		terminateConnection(connection);
	}

	// Get Data
	public static void getAllData() throws Exception {
		String query = "select * from contactlist";
		executer(query, 1);
	}

	// Get FilData
	public static void getFilteredData() throws Exception {
		String inName = getName();
		String query = "select * from contactlist where phone like '%" + inName + "%'";
		executer(query, 1);
	}

	public static void addData() throws Exception {
		String inName = getName();
		String inPhone = getNumber();
		String inEmail = getEmail();

		String query = "insert into contactlist values(" + "'" + inName + "'" + "'" + inPhone + "'" + "'" + inEmail
				+ "'" + ")";

		executer(query, 0);
	}

	public static void updateData() throws Exception {
		Scanner getOption = new Scanner(System.in);
		System.out.println(
				"What you want to update?\nPress 1 to update number\nPress 2 to update name\nPress 3 to update email\n");
		int option = getOption.nextInt();
		if (option == 1) {
			String inName = getName();
			String inNumber = getNumber();

			String query = "update contactlist set phone = '" + inNumber + "'" + "where name is " + "'" + inName + "'";

			executer(query, 0);
		}

		else if (option == 2) {
			String inNumber = getNumber();
			String inName = getName();

			String query = "update contactlist set phone = '" + inNumber + "'" + "where name is " + "'" + inName + "'";
			executer(query, 0);
		}

		else if (option == 3) {
			String inName = getName();
			String inEmail = getEmail();

			String query = "update contactlist set phone = '" + inEmail + "'" + "where name is " + "'" + inName + "'";
			executer(query, 0);
		} else {
			System.out.println("Enter Valid Data");
			Scanner getRep = new Scanner(System.in);
			System.out.println("Wanna Continue? Press y else press n: ");
			String inRep = getRep.nextLine();
			if (inRep == "y") {
				updateData();
			}
		}
	}

	public static void removeContact() throws Exception {
		String inName = getName();
		String query = "delete from contactlist where name = '" + inName + "'";
		executer(query, 0);
	}

	public static String getName() {
		Scanner name = new Scanner(System.in);
		System.out.println("enter your number: ");
		String inName = name.nextLine();
		return inName;
	}

	public static String getNumber() {
		Scanner number = new Scanner(System.in);
		System.out.println("enter your email: ");
		String inNumber = number.nextLine();
		return inNumber;
	}

	public static String getEmail() {
		Scanner email = new Scanner(System.in);
		System.out.println("enter your name: ");
		String inEmail = email.nextLine();
		return inEmail;
	}
}
