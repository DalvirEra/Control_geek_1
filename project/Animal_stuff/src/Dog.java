import java.sql.Connection;
import java.util.Date;

public class Dog extends Animal {
    public Dog(String name, String breed, String commands, Date birthDate) {
        super(name, breed, commands, birthDate);
    }

//    @Override
    public void listCommands() {
    }

    @Override
    public void listCommands(Connection connection) {

    }

    @Override
    public void teachCommand(String command) {

    }
}

