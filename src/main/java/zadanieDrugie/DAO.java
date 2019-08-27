package zadanieDrugie;


import java.sql.*;

public class DAO {


    public static Ksiazka findById(int ID) {
        String findID = "SELECT * FROM ksiazka WHERE id = ?";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        Ksiazka ksiazka = new Ksiazka();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement prestmt = connection.prepareStatement(findID);) {

            prestmt.setInt(1,ID);
            ResultSet resultSet = prestmt.executeQuery();
            resultSet.next();
               ksiazka.setId(resultSet.getInt("id"));
               ksiazka.setBook(resultSet.getString("tytul"));

        } catch (SQLException e) {
            e.printStackTrace();
        } return ksiazka;
    }

    public static void newBooks(String nowaKsiazka) {
        String newBook = "INSERT INTO ksiazka (`tytul`) VALUES (?)";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preStmt = connection.prepareStatement(newBook);) {

            preStmt.setString(1, nowaKsiazka);
            Integer liczbaDodanychRekordow = preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateById(String nowyTytul, int ID) {
        String updateID = "UPDATE ksiazka SET tytul = ? WHERE id = ?";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preStmt = connection.prepareStatement(updateID);) {

            preStmt.setString(1, nowyTytul);
            preStmt.setInt(2, ID);
            Integer liczbaUaktualnionychRekordow = preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteById(int ID) {
        String updateID = "DELETE FROM ksiazka WHERE id = ?";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preStmt = connection.prepareStatement(updateID);) {

            preStmt.setInt(1, ID);
            Integer liczbaUaktualnionychRekordow = preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer ostatnieID() {
        String selectOstatniId = "SELECT max(id) FROM ksiazka";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";
        Integer id = null;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();) {

            ResultSet resultSet = stmt.executeQuery(selectOstatniId);
            resultSet.next();
            id = resultSet.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void deleteByLastId() {
        String deleteLastID = "DELETE FROM ksiazka WHERE id = ?";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preStmt = connection.prepareStatement(deleteLastID);) {

            preStmt.setInt(1, ostatnieID());
            Integer liczbaUaktualnionychRekordow = preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listAllBooks () {
        String listBooks = "SELECT * FROM ksiazka";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();) {

            ResultSet resultSet = stmt.executeQuery(listBooks);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String tytul = resultSet.getString("tytul");
                System.out.println(id + " " + tytul);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateByLastId() {
        String updateID = "UPDATE ksiazka SET tytul = 'Naklad wyczerpany' WHERE id = ?";
        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preStmt = connection.prepareStatement(updateID);) {

            preStmt.setInt(1, ostatnieID());
            Integer liczbaUaktualnionychRekordow = preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


//    Zbudować prostego CRUD’a. Dla tabeli ksiazka należy zrobić:
//        1. obiekt DAO który będzie miał funkcje CRUDA:
//        • funkcję do czytania danych dla zadanego id (findById())
//        • funkcję dodawania nowej książki (newBooks())
//        • funkcję modyfikacji książki o zadanym id (updateById())
//        • funkcję usunięcia książki z konkretnym id (deleteById())

//        2. dodatkowo obiekt DAO będzie miał funkcję do pobierania listy wszystkich książek.

//        3. W funkcji main() korzystając z obiektu DAO tabelki ksiazka robimy operacje:
//        a. wczytujemy listę książek i wyświetlamy ją na ekranie
//        b. dodajemy 4 nowe książki
//        c. wczytujemy ponownie listę książek i wyświetlamy ją na ekranie
//        d. modyfikujemy ostatni rekord w bazie z książkami wstawiając w pole nazwa książki
//        „nakład wyczerpany” (pobieramy ostatni index Selectem z funkcją sql MAX na
//        kolumnie id)
//        e. wczytujemy ponownie listę książek i wyświetlamy ją na ekranie
//        f. usuwamy ostatni rekord
//        g. wczytujemy ponownie listę książek i wyświetlamy ją na ekranie

//        4. wrzucamy dane z połączeniem do bazy w osobną klasę DBProperties i korzystamy z niej w
//        naszym programie.