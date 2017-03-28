package com.louismau;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    static String NOUN = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\nouns\\1syllablenouns.txt";
    static String VERB = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\verbs\\1syllableverbs.txt";
    static String ADJ = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\adjectives\\1syllableadjectives.txt";
    static String COLOURS = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\colours\\colours.txt";
    static String ANIMALS = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\animals\\Animal-words.txt";
    static String DESC = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\description.txt";

    public static void main(String[] args) {

        ArrayList<String> noun = new ArrayList<>();
        ArrayList<String> verb = new ArrayList<>();
        ArrayList<String> adjective = new ArrayList<>();
        ArrayList<String> colour = new ArrayList<>();
        ArrayList<String> animal = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();

        readInDictionary(noun, verb, adjective, colour, animal, descriptions);
        Random rdm = new Random();
        String colourName = colour.get(rdm.nextInt(colour.size()));
        String animalName = animal.get(rdm.nextInt(animal.size()));
        System.out.println(colourName+animalName);

        System.out.println("####################");
        for(int i = 0; i < 10; i++) {
            generateHunter(adjective, animal);
            generateItem(i,descriptions,colour,noun);
        }
        System.out.println("####################");
        for (int i = 0; i < 7; i++)
        {
            generateMH(i,adjective, noun);
        }
        System.out.println("####################");
        for (int i = 0; i < 7; i++) {
            generateMonster(colour, adjective, animal);
        }


    }

    private static void readInDictionary(ArrayList noun, ArrayList verb, ArrayList adjective, ArrayList colour, ArrayList animal, ArrayList desc) {

        Path nounFile = Paths.get(NOUN);
        Path verbFile = Paths.get(VERB);
        Path adjFile = Paths.get(ADJ);
        Path colourFile = Paths.get(COLOURS);
        Path animalFile = Paths.get(ANIMALS);
        Path descFile = Paths.get(DESC);

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
            BufferedReader reader5 = Files.newBufferedReader(descFile);
            line = null;
            while ((line = reader5.readLine()) != null){
                desc.add(line);
            }

            reader.close();
            reader1.close();
            reader2.close();
            reader3.close();
            reader4.close();
            reader5.close();
        }
        catch (Exception e)
        {
            System.out.println("File Not Found");
            e.printStackTrace();
        }

    }
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
    private static void generateHunter(ArrayList<String> adj, ArrayList<String> noun)
    {
        Random rdm = new Random();
        int id = rdm.nextInt(250);
        String name = adj.get(rdm.nextInt(adj.size())) +" "+ noun.get(rdm.nextInt(noun.size()));
        int age = rdm.nextInt(100);
        char gender;
        if(rdm.nextInt()%2 == 0)
        {
            gender = 'M';
        }
        else
            gender = 'F';
        int exp = rdm.nextInt(1000);
        int gold = rdm.nextInt(1000000);


        System.out.println("(" + id + "," + name + "," + age + "," + gender + "," + exp + "," + gold +")");
    }

        /*
        <Mission Holder>
        ID
        Age
        Name
        Reputation
        Balance
         */
    private static void generateMH(int id, ArrayList<String> adj, ArrayList<String> noun)
    {
        Random rdm = new Random();
        int d = rdm.nextInt(250);
        String name = adj.get(rdm.nextInt(adj.size())) +" "+ noun.get(rdm.nextInt(noun.size()));
        int age = rdm.nextInt(100);
        int rep = rdm.nextInt(1000);
        int gold = rdm.nextInt(10000000);


        System.out.println("("+ id + "," + age + "," + name + "," + rep + "," + gold +")");


    }
        /*
        <TEAM>
        Name
        Rank
        HunterID
         */
    private static void generateTeam(int id, ArrayList<String> adj, ArrayList<String> noun)
    {
        Random rdm = new Random();
        String name = adj.get(rdm.nextInt(adj.size())) +" "+ noun.get(rdm.nextInt(noun.size()));
        String[] rank = {"D","E","C","B","A","S"};
        String teamRank = rank[rdm.nextInt(rank.length)];
        int hunterID = id;

        System.out.println("(" +name+ ","+ rank +","+ hunterID +")");
    }
        /*
        <MONSTER>
        ID
        Name
        Location
        Ferocity
        Remaining
        Domesticated
         */
        private  static void generateMonster(ArrayList<String> colour, ArrayList<String> adj, ArrayList<String> animal)
        {
            Random rdm = new Random();
            int id = rdm.nextInt(100);
            String name = colour.get(rdm.nextInt(colour.size())) + adj.get(rdm.nextInt(adj.size())) + animal.get(rdm.nextInt(animal.size()));
            String location = adj.get(rdm.nextInt(adj.size())) + colour.get(rdm.nextInt(colour.size()));
            int ferocity = rdm.nextInt(150);
            int remaining = 100;
            char domesticated;
            if(rdm.nextInt()%2 == 0)
            {
                domesticated = 'T';
            }
            else
                domesticated = 'F';

            System.out.println("("+id+","+name+","+location+","+ferocity+","+remaining+","+domesticated+")");
        }
    /*
        <ITEM>
        ID
        HunterID
        MissionID
        Name
        Rank
        Rarity
        Value
         */
    private static void generateItem(int hunterID,ArrayList<String> desc, ArrayList<String> colour, ArrayList<String> noun)
    {
        Random rdm = new Random();
        int id = rdm.nextInt(100);
        int hID = hunterID;
        int mID;
        String name = colour.get(rdm.nextInt(colour.size())) + noun.get(rdm.nextInt(noun.size()));
        String[] rank = {"D","E","C","B","A","S"};
        String itemRank = rank[rdm.nextInt(rank.length)];
        int rarity = rdm.nextInt(100);
        int itemValue;
        switch (itemRank)
        {
            case "S":
                itemValue = rdm.nextInt(1000000);
                break;
            case "A":
                itemValue = rdm.nextInt(100000);
                break;
            case "B":
                itemValue = rdm.nextInt(10000);
                break;
            case "C":
            case "E":
            case "D":
                itemValue = rdm.nextInt(1000);
                break;
            default:
             itemValue = 0;
            break;
        };
        System.out.println("(" +id+","+hID+","+name+","+itemRank+","+rarity+","+itemValue+ ")");
        generateItemDesc(name,desc,rdm);

    }
    /*
    <ITEM NAME>
    Name
    Description
     */
    private static void generateItemDesc(String name, ArrayList<String> desc,Random rdm) {

        String description = desc.get(rdm.nextInt(desc.size()));

        System.out.println("(" +name+ ","+description+ ")");
    }

    /*
        <Hunting Missions>
        ID
        HunterID
        MissionHolderID
        expReward
        goldReward
        description
        Deadline
        Start Time
        acceptTime
        CompletionTime
        ForfeitTime
        Type
        MonsterID
         */
    private static void generateHuntingMission(ArrayList<String> desc)
    {
        Random rdm = new Random();
        int id = rdm.nextInt(1000);
        int hunterID;
        int missionHolderID;
        int expReward = rdm.nextInt(1000000);
        int goldReward = rdm.nextInt(600000);
        String description = desc.get(rdm.nextInt(desc.size()));
        String deadline;
        String missionType;
        int monsterID;

        generateHuntingDifficulty(goldReward,expReward);
    }
    private static void generateHuntingDifficulty(int gold, int exp)
    {
        Random rdm = new Random();
        String[] rank = {"D","E","C","B","A","S"};
        String difficulty;
        int determine = (gold + exp)/2;

        if (determine >= 500000)
        {
            difficulty = "S";
        }
        else if (determine > 200000)
        {
            if(determine % 2 == 0)
            {
                difficulty = "S";
            }
            else
                difficulty = "A";
        }
        else
            difficulty = rank[rdm.nextInt(rank.length-1)];

        System.out.println("("+difficulty+","+exp+","+gold+")");

    }

        /*
        <Item Foraging Mission>
        ID
        hunterID
        MissionHolder ID
        itemID
        Deadline
        Description
        ExpReward
        GoldReward
        StartTime
        AcceptTime
        CompleteTime
        ForfeitTime
         */
    private static void generateItemMission(int iID, int mhID,ArrayList<String> desc)
    {
        Random rdm = new Random();
        int id = rdm.nextInt(1000);
        int hunterID;
        int missionHolderID;
        int itemID;
        int expReward = rdm.nextInt(1000000);
        int goldReward = rdm.nextInt(600000);
        String description = desc.get(rdm.nextInt(desc.size()));
        String deadline;
        String missionType;
        int monsterID;
    }


}

