import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    private Connection connection;

    public Menu(Connection connection) {
        this.connection = connection;
    }

    public void start() throws CounterException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. New animals");
            System.out.println("2. See animal");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    listAnimals();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Error.");
            }
        }
    }

    private void addAnimal() throws CounterException {
            System.out.println("Enter a name:");
            String name = scanner.nextLine();
            System.out.println("Enter a type of animal:");
            String species = scanner.nextLine();
            System.out.println("Enter commands known to animals:");
            String commands = scanner.nextLine();
            System.out.println("Enter a birthdate (гггг-мм-дд):");
            String birthDate = scanner.nextLine();

            try {
                AnimalManager.addAnimal(connection, name, species, commands, birthDate);
                System.out.println("Animal added.");
            } catch (SQLException e) {
                System.err.println("Error in adding animal: " + e.getMessage());
            }
        }

    private void listAnimals() {
        try {
            List<Animal> animals = AnimalManager.getAllAnimals();

            // Вывод списка
            System.out.println("Animals list:");
            for (Animal animal : animals) {
                System.out.println(animal); 
            }

            System.out.println("Total amount of animals: " + animals.size());
        } catch (SQLException e) {
            System.out.println("Error in recieving amounts: " + e.getMessage());
        }
    }

}
