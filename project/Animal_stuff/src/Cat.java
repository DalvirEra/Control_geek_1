import java.sql.Connection;
import java.util.Date;

public class Cat extends Animal {
    public Cat(String name, String breed, String commands, Date birthDate) {
        super(name, breed, commands, birthDate);
    }

//    @Override
    public void listCommands() {
        // Реализация вывода списка команд для кошки
    }

    @Override
    public void listCommands(Connection connection) {

    }

    @Override
    public void teachCommand(String command) {
        // Реализация обучения кошки новой команде
    }
}

