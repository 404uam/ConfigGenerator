package com.louismau;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    static String NOUN = "C:\\Users\\Louis\\IdeaProjects\\untitled\\src\\nouns\\1syllablenouns.txt";
    static String VERB = "C:\\Users\\Louis\\IdeaProjects\\untitled\\src\\verbs\\1syllableverbs.txt";
    static String ADJ = "C:\\Users\\Louis\\IdeaProjects\\untitled\\src\\adjectives\\1syllableadjectives.txt";

    public static void main(String[] args) {

        ArrayList<String> noun = new ArrayList<>();
        ArrayList<String> verb = new ArrayList<>();
        ArrayList<String> adjective = new ArrayList<>();

        readInDictionary(noun, verb, adjective);
        Random rdm = new Random();

        System.out.print(verb.get(rdm.nextInt(verb.size())) + adjective.get(rdm.nextInt(adjective.size())) + noun.get(rdm.nextInt(noun.size())));

    }

    private static void readInDictionary(ArrayList noun, ArrayList verb, ArrayList adjective) {

        Path nounFile = Paths.get(NOUN);
        Path verbFile = Paths.get(VERB);
        Path adjFile = Paths.get(ADJ);

        try(BufferedReader reader = Files.newBufferedReader(nounFile)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                line = line.substring(0,1).toUpperCase()+line.substring(1).toLowerCase();
               noun.add(line);
            }
            BufferedReader reader1 = Files.newBufferedReader(verbFile);
            line = null;
            while ((line = reader1.readLine()) != null) {
                line = line.substring(0,1).toUpperCase()+line.substring(1).toLowerCase();
                verb.add(line);
            }
            BufferedReader reader2 = Files.newBufferedReader(adjFile);
            line = null;
            while ((line = reader2.readLine()) != null) {
                line = line.substring(0,1).toUpperCase()+line.substring(1).toLowerCase();
                adjective.add(line);
            }
            reader.close();
            reader1.close();
            reader2.close();
        }
        catch (Exception e)
        {
            System.out.println("File Not Found");
            e.printStackTrace();
        }

    }

}
