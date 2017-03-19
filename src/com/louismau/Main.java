package com.louismau;
import java.io.BufferedReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    static String NOUN = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\nouns\\1syllablenouns.txt";
    static String VERB = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\verbs\\1syllableverbs.txt";
    static String ADJ = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\adjectives\\1syllableadjectives.txt";
    static String COLOURS = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\colours\\colours.txt";
    static String ANIMALS = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\animals\\Animal-words.txt";

    public static void main(String[] args) {

        ArrayList<String> noun = new ArrayList<>();
        ArrayList<String> verb = new ArrayList<>();
        ArrayList<String> adjective = new ArrayList<>();
        ArrayList<String> colour = new ArrayList<>();
        ArrayList<String> animal = new ArrayList<>();

        readInDictionary(noun, verb, adjective, colour, animal);
        Random rdm = new Random();


        System.out.print(colour.get(rdm.nextInt(colour.size()))+ verb.get(rdm.nextInt(verb.size())) + adjective.get(rdm.nextInt(adjective.size())) + noun.get(rdm.nextInt(noun.size())));


        /*
        <HUNTER>
        ID
        Name
        Age
        Gender
        Exp
        Balance
        teamName
         */

        /*
        <MONSTER>
        ID
        Name
        Location
        Ferocity
        Remaining
        Domesticated
         */

        /*
        <Mission Holder>
        ID
        Age
        Name
        Reputation
        Balance
         */

        /*
        <ITEM>
        ID
        Name
        HunterID
        MissionID
        Rank
        Rarity
        Value
         */
        /*
        <ITEM NAME>
        Name
        Description
         */

        /*
        <TEAM>
        Name
        Rank
        HunterID
         */

        /*
        <Hunting Missions>
        ID
        HunterID
        MissionHolderID
        Deadline
        Start Time
        type
        ExpReward
        goldRewad
        acceptTime
        CompletionTime
        ForfeitTime
        MonsterID
         */

        /*
        <Item Foraging Mission>
        ID
        itemID
        Difficulty
        Deadline
        StartTime
        Type
        ExpReward
        GoldReward
        AcceptTime
        CompleteTime
        ForfeitTime
         */

    }

    private static void readInDictionary(ArrayList noun, ArrayList verb, ArrayList adjective, ArrayList colour, ArrayList animal) {

        Path nounFile = Paths.get(NOUN);
        Path verbFile = Paths.get(VERB);
        Path adjFile = Paths.get(ADJ);
        Path colourFile = Paths.get(COLOURS);
        Path animalFile = Paths.get(ANIMALS);

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
            BufferedReader reader3 = Files.newBufferedReader(colourFile);
            line = null;
            while ((line = reader3.readLine()) != null) {
                line = line.substring(0,1).toUpperCase()+line.substring(1).toLowerCase();
                colour.add(line);
            }
            BufferedReader reader4 = Files.newBufferedReader(animalFile);
            line = null;
            while ((line = reader4.readLine()) != null) {
                animal.add(line);
            }
            reader.close();
            reader1.close();
            reader2.close();
            reader3.close();
            reader4.close();
        }
        catch (Exception e)
        {
            System.out.println("File Not Found");
            e.printStackTrace();
        }

    }

}
