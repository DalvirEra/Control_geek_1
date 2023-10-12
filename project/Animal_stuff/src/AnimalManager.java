import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalManager {

    public static void addAnimal(Connection connection, String name, String species, String commands, String birthDateStr) throws SQLException, CounterException {

        java.sql.Date sqlBirthDate = java.sql.Date.valueOf(birthDateStr);

        String sql = "INSERT INTO animal_list (Name, animals, Commands, Birthday) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, species);
            preparedStatement.setString(3, commands);
            preparedStatement.setDate(4, sqlBirthDate);
            preparedStatement.executeUpdate();
        }
        try (Counter counter = new Counter()) {
            counter.add();
        } catch (Exception e) {
            throw new CounterException("Ошибка при работе с счетчиком.");
        }

    }

    public static String determineAnimalClass(String species) {
        if ("Cat".equalsIgnoreCase(species) || "Dog".equalsIgnoreCase(species) || "Hampster".equalsIgnoreCase(species)) {
            return "home";
        } else {
            return "packing";
        }
    }

    public static List<Animal> getAllAnimals() throws SQLException {
        List<Animal> animals = new ArrayList<>();

        // Выполните SQL-запрос для выбора всех животных из таблицы
        String sql = "SELECT * FROM animal_list";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                // Создайте экземпляр животного и добавьте его в список
                Animal animal = AnimalManager.createAnimal(
                        resultSet.getString("Name"),
                        resultSet.getString("animals"),
                        resultSet.getString("Commands"),
                        resultSet.getDate("Birthday")
                );

                if (animal != null) {
                    animals.add(animal);
                }
            }
        }

        return animals;
    }

    public static Animal createAnimal(String name, String species, String commands, Date birthDateStr) {

        // В зависимости от вида (species) создаем экземпляр нужного класса
        if ("Dogs".equalsIgnoreCase(species) || "Dog".equalsIgnoreCase(species)) {
            return new Dog(name, species, commands, birthDateStr);
        } else if ("Cats".equalsIgnoreCase(species) || "Cat".equalsIgnoreCase(species)) {
            return new Cat(name, species, commands, birthDateStr);
        } else if ("Hampsters".equalsIgnoreCase(species) || "Hampster".equalsIgnoreCase(species)) {
            return new Ham(name, species, commands, birthDateStr);
        } else if ("Horses".equalsIgnoreCase(species) || "Horse".equalsIgnoreCase(species)) {
            return new Horse(name, species, commands, birthDateStr);
        } else if ("Donkeys".equalsIgnoreCase(species) || "Donkey".equalsIgnoreCase(species)) {
            return new Donkey(name, species, commands, birthDateStr);
        } else {
            // В случае неизвестного вида животного можно вернуть null или выбрать другую стратегию
            return null;
        }


    }
}

