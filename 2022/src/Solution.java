package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class Solution {

    public String sampleFile = null;
    public boolean runDefaultSample;

    // Run solution with input.txt
    public Solution() {
        setup();
    }

    // Run solution with provided file
    public Solution(String sampleFile) {
        this.sampleFile = sampleFile;
        setup();
    }

    // Run solution with sample.txt
    public Solution(boolean runDefaultSample) {
        this.runDefaultSample = runDefaultSample;
        setup();
    }

    private void setup() {
        // Get Concrete Class name that extends it, i.e. src.Day1.Answer
        String concreteClass = this.getClass().getCanonicalName();
        // input folder i.e. src/Day1
        String inputFolder = concreteClass.replace('.', '/').replaceFirst("/Answer", "");
        String day = "Day " + inputFolder.replaceAll("[\\D]", "");
        
        String baseFolder = "2022/" + inputFolder;

        String inputFile = baseFolder + "/input.txt";
        if (this.runDefaultSample) {
            inputFile = baseFolder + "/sample.txt";
        }
        if (this.sampleFile != null) {
            inputFile = baseFolder + "/" + this.sampleFile + ".txt";
        }

        System.out.println("=-=-=-=-= " + day + " =-=-=-=-=");
        
        try {
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader reader = new BufferedReader(fileReader);
            if (reader.ready()) {
                solve(reader);
            }
            System.out.println();
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File: " + inputFile + " not found");
        } catch (IOException ex) {
            System.out.println("Error reading file " + inputFile);
        }
    }

    protected abstract void solve(BufferedReader reader) throws IOException;
}
