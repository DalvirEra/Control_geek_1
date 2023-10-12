import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

public abstract class Animal {
    private String name;
    private String breed;
    private String commands;
    private Date birthDate;

    public Animal(String name, String breed, String commands, Date birthDate) {
        this.name = name;
        this.breed = breed;
        this.commands = commands;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }


public int calculateAgeInYears() {
    Calendar birthCalendar = Calendar.getInstance();
    birthCalendar.setTime(birthDate);

    Calendar currentCalendar = Calendar.getInstance();

    int years = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

    // Проверяем, если текущая дата меньше даты рождения по дню и месяцу, уменьшаем возраст на 1 год
    if (currentCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
            (currentCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                    currentCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
        years--;
    }

    return years;
}
    public abstract void listCommands(Connection connection);

    public abstract void teachCommand(String command);

    @Override
    public String toString() {
        int age = calculateAgeInYears();
        String animalClass = AnimalManager.determineAnimalClass(breed);
        return "Name: " + name + ", Animal: " + breed + ", birthday: " + age + ", Animal type: " + animalClass + ", Commands: " + commands;
    }

}
