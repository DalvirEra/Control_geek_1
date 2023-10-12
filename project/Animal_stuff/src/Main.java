import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws CounterException {
        try {
            // Получаем соединение с базой данных
            Connection connection = DatabaseConnection.getConnection();

            // Передаем это соединение в ваше меню при его инициализации
            Menu menu = new Menu(connection);

            // Запускаем меню
            menu.start();

            // Закрываем соединение, когда оно больше не нужно
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка исключения, если что-то пошло не так при подключении к базе данных.
        }
    }
}
