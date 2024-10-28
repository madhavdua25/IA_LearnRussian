package model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class DB {
	private Connection conn = null;
	private static DB db = new DB();

	private DB() {
		try (InputStream input = new FileInputStream("config.properties")) {
			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			Properties connectionProps = new Properties();
			connectionProps.put("user", prop.getProperty("db.user"));
			connectionProps.put("password", prop.getProperty("db.password"));

			String serverName = prop.getProperty("db.host");
			String port = prop.getProperty("db.port");
			String db = prop.getProperty("db.instance");

			conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + port + "/" + db, connectionProps);

			System.out.println("Connected to database");
		} catch (Exception ex) {
			System.err.println(ex);
			ex.printStackTrace(System.err);
		}
	}

	/** Returns an array list of the leagues in the database. */
	public static ArrayList<Student> loadStudents() {
		ArrayList<Student> list = new ArrayList<>();
		String queryString = " select student.student_id, name " +
				" from student  " +
				" order by name ";

		try (
				PreparedStatement queryStmt = db.conn.prepareStatement(queryString);
				ResultSet rs = queryStmt.executeQuery();) {

			while (rs.next()) {
				int student_id = rs.getInt("student_id");
				String name = rs.getString("name");

				Student student = new Student(student_id, name);

				list.add(student);
			}

		} catch (Exception ex) {
			System.err.println(ex);
			ex.printStackTrace(System.err);
		}

		return list;
	}

	public static ArrayList<Question> loadQuestions() {
		ArrayList<Question> list = new ArrayList<>();
		String queryString = " select question.question_id, text, correct_answer, incorrect1, incorrect2, incorrect3 " +
				" from question  " +
				" order by question_id ";

		try (
				PreparedStatement queryStmt = db.conn.prepareStatement(queryString);
				ResultSet rs = queryStmt.executeQuery();) {

			while (rs.next()) {
				int question_id = rs.getInt("question_id");
				String text = rs.getString("text");
				String correct_answer = rs.getString("correct_answer");
				String incorrect1 = rs.getString("incorrect1");
				String incorrect2 = rs.getString("incorrect2");
				String incorrect3 = rs.getString("incorrect3");

				Question question = new Question(question_id, text, correct_answer, incorrect1, incorrect2, incorrect3);

				list.add(question);
			}

		} catch (Exception ex) {
			System.err.println(ex);
			ex.printStackTrace(System.err);
		}

		return list;
	}

	/** Loads a single league given an id. */
	// public static League loadLeague(int leagueId) {
	// 	String queryString = " select league.league_id, league_name " +
	// 			" from league  " +
	// 			" where league_id = ? ";

	// 	try (
	// 			PreparedStatement queryStmt = db.conn.prepareStatement(queryString)) {
	// 		queryStmt.setInt(1, leagueId);

	// 		try (ResultSet rs = queryStmt.executeQuery()) {

	// 			if (rs.next()) {
	// 				String leagueName = rs.getString("league_name");

	// 				return new League(leagueId, leagueName, false);
	// 			}
	// 		}
	// 	} catch (Exception ex) {
	// 		System.err.println(ex);
	// 		ex.printStackTrace(System.err);
	// 	}

	// 	return null;
	// }

	/** Adds a new league to the database. */
	public static void insertStudent(String name, int val) {
		String query = "insert into student(student_id, name ) values (?,?)";

		try (PreparedStatement insertStmt = db.conn.prepareStatement(query)) {

			insertStmt.setInt(1, val);
			insertStmt.setString(2, name);
			
			insertStmt.executeUpdate();
		} catch (Exception ex) {
			System.err.println(ex);
			ex.printStackTrace(System.err);
		}
	}

	/** Updates the name of a league in the database. */
	// public static void updateLeague(League league) {
	// 	String query = "update league set league_name = ? where league_id = ?";

	// 	try (PreparedStatement updateStmt = db.conn.prepareStatement(query)) {

	// 		updateStmt.setString(1, league.getLeagueName());
	// 		updateStmt.setInt(2, league.getLeagueId());

	// 		updateStmt.executeUpdate();
	// 	} catch (Exception ex) {
	// 		System.err.println(ex);
	// 		ex.printStackTrace(System.err);
	// 	}
	// }

	/** Deletes the given league from the database. */
	public static void deleteStudent(int leagueId) {
		String query = "delete from student where student_id = ?";

		try (PreparedStatement updateStmt = db.conn.prepareStatement(query)) {

			updateStmt.setInt(1, leagueId);
			updateStmt.executeUpdate();
		} catch (Exception ex) {
			System.err.println(ex);
			ex.printStackTrace(System.err);
		}
	}

}
