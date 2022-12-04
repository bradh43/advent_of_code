import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

abstract class BaseDay {

    public String day;

    public BaseDay() {
        // Get Concrete Class name that extends it, i.e. Day1
        this.day = this.getClass().getCanonicalName();
        final String INPUT_FILE = "2022/" + day + ".txt";

        System.out.println("===== " + day + " =====");

        try {
            FileReader fileReader = new FileReader(INPUT_FILE);
            BufferedReader reader = new BufferedReader(fileReader);
            if (reader.ready()) {
                solve(reader);
            }
            System.out.println();
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File: " + INPUT_FILE + " not found");
        } catch (IOException ex) {
            System.out.println("Error reading file " + INPUT_FILE);
        }
    }

    abstract void solve(BufferedReader reader) throws IOException;
}
