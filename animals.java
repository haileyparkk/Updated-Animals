import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class animals {
    private static String name;
    private static String color;
    private static String sound;
    private static String species;
    private static int maxAgeYear;
    private static int maxAgeMonth;
    private static int maxAgeDay;

    /**
     * a constructor
     * @param name the animal's name
     * @param color the animal's color
     * @param sound the animal's sound
     * @param species the animal's species
     */

    public animals(String name, String color, String sound, String species) {
        this.name = name;
        this.color = color;
        this.sound = sound;
        this.species = species;
    }

    //ALL THE NECESSARY SETTERS AND GETTERS

    /**
     * the setter for name
     * @param inputName the current animal's name
     */

    public static void setName(String inputName) {
        name = inputName;
    }

    /**
     * the setter for color
     * @param inputColor the current animal's color
     */

    public static void setColor(String inputColor) {
        color = inputColor;
    }

    /**
     * the setter for species
     * @param species1 the current animal's species
     */

    public static void setSpecies(String species1) {
        species = species1;
    }

    /**
     * the setter for the current maximum year
     * @param year the current max year
     */

    public static void setYear(int year) {
        maxAgeYear = year;
    }

    /**
     * the setter for the current maximum month
     * @param month the current max month
     */

    public static void setMonth(int month) {
        maxAgeMonth = month;
    }

    /**
     * the setter for the current maximum day
     * @param day the current max day
     */

    public static void setDay(int day) {
        maxAgeDay = day;
    }

    /**
     * the setter for the animal's sound
     * @param species the species the animal is
     */

    public static void setSound(String species) {
        species = species.toLowerCase();
        if (species.equals("dog")) {
            sound = "bark";
        } else if (species.equals("cat")) {
            sound = "meow";
        } else if (species.equals("sheep")) {
            sound = "baa";
        } else {
            System.out.println("Not a valid species! Remember, the animal must be a dog, cat, or sheep!");
        }
    }

    //THE MAIN METHOD TO SOLVE THE PROBLEM

    /**
     * the main method that discovers the animal of the greatest age
     * @param information the current animal's information
     */

    public static void findMaxAge(String information) {
        String[] splitStuff = information.split(" ");
        String theName = splitStuff[0];
        String theColor = splitStuff[1];
        String theSpecies = splitStuff[2];
        try {
            int birthMonth = Integer.parseInt(splitStuff[3]);
            int birthDay = Integer.parseInt(splitStuff[4]);
            int birthYear = Integer.parseInt(splitStuff[5]);
            LocalDate today = LocalDate.now();
            LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
            int yearsBetween = Period.between(birthDate, today).getYears();
            int monthsBetween = Period.between(birthDate, today).getMonths();
            int daysBetween = Period.between(birthDate, today).getDays();
            if (birthDate.compareTo(today) > 0) {
                System.out.println("Error: the animal's/at least one of the animals' birthday is in the future. We will remove it from the list.");
            }
            if (yearsBetween > maxAgeYear) {
                setEverything(yearsBetween, monthsBetween, daysBetween, theName, theColor, theSpecies);
            } else if (yearsBetween == maxAgeYear) {
                if (monthsBetween > maxAgeMonth) {
                    setEverything(yearsBetween, monthsBetween, daysBetween, theName, theColor, theSpecies);
                } else if (monthsBetween == maxAgeMonth) {
                    if (daysBetween > maxAgeDay) {
                        setEverything(yearsBetween, monthsBetween, daysBetween, theName, theColor, theSpecies);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please check your formatting! Remember, there are no spaces before or after the comma." +
                    " The program will output the oldest animal of the correctly formatted portion of the list until this is fixed.");
        }
    }

    /**
     * the method that sets all the parameters to hold the current oldest animal's information
     * @param maxAgeYear the current maximum year
     * @param maxAgeMonth the current maximum month
     * @param maxAgeDay the current maximum day
     * @param name the current name
     * @param color the current color
     * @param species the current species
     */

    public static void setEverything(int maxAgeYear, int maxAgeMonth, int maxAgeDay, String name, String color, String species) {
        animals.setYear(maxAgeYear);
        animals.setMonth(maxAgeMonth);
        animals.setDay(maxAgeDay);
        animals.setSound(species);
        animals.setName(name);
        animals.setColor(color);
        animals.setSpecies(species);
    }

    //WHERE THE USER INPUTS THEIR ANIMALS

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter each animal's information in the following order with commas separating each animal. Please remember" +
                " that the animal can only be one of the following three species (dog, cat, or sheep): name color species birth month birth day birth year");
        System.out.println("Please also refrain from putting spaces before or after your comma if you are listing animals. Here is an example of the format to use if you are listing multiple animals: " +
                "Jack white cat 01 19 2019,bryan brown dog 12 21 2019");
        System.out.println("Please note that if multiple animals have the same birthday and that birthday is the earliest" +
                " of all the birthdays in the list, the program will output the first animal with that birthday.");
        String[] splitString = scanner.nextLine().split(",");
        for (int i = 0; i < splitString.length; i++) {
            String information = splitString[i];
            findMaxAge(information);
        }
        System.out.println(name + ", " + color + " " + species + " says " + sound + "!");
    }
}
