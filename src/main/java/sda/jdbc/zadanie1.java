package sda.jdbc;

import java.sql.*;

public class zadanie1 {

    private final static String sqlInsert = "INSERT INTO uzytkownik" +
            "(`imie`,`nazwisko`)" +
            "VALUES ('Kazik', 'Deyna')";

    private static int maxID () {
        String maxId = "SELECT max(id) FROM uzykownik";
        return maxID();
    }

    private final static String sqlUpdate = "UPDATE INTO uzytkownik " +
            "('nazwisko')" +
            "VALUES ('Robak') WHERE 'id' = max(id)";

    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        PreparedStatement preStmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM uzytkownik");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                System.out.println(id + " " + imie + " " + nazwisko);
            }

            System.out.println("\nTutaj dodajemy użykownika: Kazik Deyna.\n");

            preStmt = connection.prepareStatement(sqlInsert);
            preStmt.execute();

            System.out.println("Tutaj lista z dodanym użykownikiem.\n");

            ResultSet resultSet1 = stmt.executeQuery("SELECT * FROM uzytkownik");
            while (resultSet1.next()) {
                Integer id = resultSet1.getInt("id");
                String imie = resultSet1.getString("imie");
                String nazwisko = resultSet1.getString("nazwisko");
                System.out.println(id + " " + imie + " " + nazwisko);
            }






        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
