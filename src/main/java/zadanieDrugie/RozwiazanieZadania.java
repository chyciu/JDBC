package zadanieDrugie;

public class RozwiazanieZadania {

    public static void main(String[] args) {

        Ksiazka book1 = DAO.findById(1);
        System.out.println("Ksiązka szukana po ID.");
        System.out.println(book1.toString());

        System.out.println("\nLista wszystkich książek.");
        DAO.listAllBooks();

        System.out.println("\nTeraz program robi dodawanie nowych książek.");
        DAO.newBooks("W pustyni i w puszczy.");
        DAO.newBooks("Nowa ksiazka");
        DAO.newBooks("Nowa nowela");
        DAO.newBooks("Nowa trylogia");

        System.out.println("Teraz program modyfikuje tytul ksiazki przez ID");
        DAO.updateById("Krzyzacy", 6);

        System.out.println("\nTeraz program usuwa książkę po zadanym ID.");
        DAO.deleteById(13);

        System.out.println("\nTeraz program usuwa książkę z ostatnim ID na aktualnej liście.");
        DAO.deleteByLastId();

        System.out.println("\nTeraz program zmienia tytul ostatniej ksiażki na 'Naklad wyczerpany'.");
        DAO.updateByLastId();

        System.out.println("\nTeraz program wyświetla akualną listę.");
        DAO.listAllBooks();





    }
}
