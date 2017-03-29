package com.louismau;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    static String DESC = "C:\\Users\\Louis\\Documents\\ConfigGenerator\\src\\description.txt";

    public static void main(String[] args) {

        ArrayList<String> noun = new ArrayList<>();
        ArrayList<String> verb = new ArrayList<>();
        ArrayList<String> adjective = new ArrayList<>();
        ArrayList<String> colour = new ArrayList<>();
        ArrayList<String> animal = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();

        Path sqlFile = Paths.get("./insert.sql");
        BufferedWriter bw;

        readInDictionary(noun, verb, adjective, colour, animal, descriptions);

        try {
           bw = Files.newBufferedWriter(sqlFile);
           for(int i = 0; i< 10; i++)
           {
               generateHunter(bw,i,adjective,noun);
           }
           bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }




    }

    private static void readInDictionary(ArrayList<String> noun, ArrayList<String> verb, ArrayList<String> adjective, ArrayList<String> colour, ArrayList<String> animal, ArrayList<String> desc) {

        Path nounFile = Paths.get(NOUN);
        Path verbFile = Paths.get(VERB);
        Path adjFile = Paths.get(ADJ);
        Path colourFile = Paths.get(COLOURS);
        Path animalFile = Paths.get(ANIMALS);
        Path descFile = Paths.get(DESC);

        try(BufferedReader reader = Files.newBufferedReader(nounFile)) {
            String line;
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
    private static void generateHunter(BufferedWriter bw,int id, ArrayList<String> adj, ArrayList<String> noun)
    {
        Random rdm = new Random();
        String name = adj.get(rdm.nextInt(adj.size())) +" "+ noun.get(rdm.nextInt(noun.size()));
        int age = rdm.nextInt(100);
        char gender;
        if(rdm.nextInt()%2 == 0)
        {
            gender = 'M';
        }
        else
            gender = 'F';
        int exp = rdm.nextInt(10000);
        int gold = rdm.nextInt(1000000);

        try {
            bw.write("insert into Hunter values"+"(" + id + "," + name + "," + age + "," + gender + "," + exp + "," + gold +",NULL);");
            bw.newLine();
            generateHunterExp(bw,exp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("(" + id + "," + name + "," + age + "," + gender + "," + exp + "," + gold +");");
    }

    private static void generateHunterExp(BufferedWriter bw, int exp)
    {
        int lvl = 1;
        if(exp >= 9000)
        {
            lvl = 100;
        }
        else if (exp >= 5000)
        {
            lvl = 50;
        }
        else if (exp >= 2500)
        {
            lvl = 25;
        }
        else if (exp >= 1000)
        {
            lvl = 10;
        }
        try{
            bw.write("insert into Hunter_experience values("+exp+","+lvl+");");
            bw.newLine();
        }
        catch (Exception e)
        {

        }
        System.out.println("insert into Hunter_experience values("+exp+","+lvl+");");

    }
        /*
        <Mission Holder>
        ID
        Age
        Name
        Reputation
        Balance
         */
    private static void generateMH(BufferedWriter bw,int id, ArrayList<String> adj, ArrayList<String> noun)
    {
        Random rdm = new Random();
        String name = adj.get(rdm.nextInt(adj.size())) +" "+ noun.get(rdm.nextInt(noun.size()));
        int age = rdm.nextInt(100);
        int rep = rdm.nextInt(1000);
        int gold = rdm.nextInt(10000000);

        try{
            bw.write("instert into MissionHolder values("+id+"," + age + "," + name + "," + rep + "," + gold +");");
            generateMHReputation(bw,gold);
        }
        catch(Exception e)
        {
        }

        System.out.println("instert into MissionHolder values("+ id + "," + age + "," + name + "," + rep + "," + gold +");");
    }
    private static void generateMHReputation(BufferedWriter bw, int gold)
    {
        String reputation;
       if (gold >= 500000)
       {
           reputation = "S";
       }
       else if(gold >= 235000)
       {
           reputation = "A";
       }
       else if(gold >= 150000)
       {
           reputation = "B";
       }
       else if (gold >= 50000)
       {
           reputation = "C";
       }
       else
           reputation = "D";
       try{
           bw.write("insert into MissionHolder_reputation values(" +gold+","+reputation+ ");");
       }
       catch (Exception e)
       {
       }

       System.out.println("(" +gold+","+reputation+ ")");
    }
        /*
        <TEAM>
        Name
        Rank
        HunterID
         */
    private static void generateTeam(int id,BufferedWriter bw, ArrayList<String> adj, ArrayList<String> noun)
    {
        Random rdm = new Random();
        String name = adj.get(rdm.nextInt(adj.size())) +" "+ noun.get(rdm.nextInt(noun.size()));
        String[] rank = {"D","E","C","B","A","S"};
        String teamRank = rank[rdm.nextInt(rank.length)];
        int hunterID = id;

        try{
            bw.write("insert into Team values(" +name+ ","+ rank +","+ hunterID +");");
        }
        catch (Exception e)
        {

        }
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
        private  static void generateMonster(BufferedWriter bw,ArrayList<String> colour, ArrayList<String> adj, ArrayList<String> animal)
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

            try{
                bw.write("insert into Monster values("+id+","+name+","+location+","+ferocity+","+remaining+","+domesticated+");");
            }
            catch (Exception e)
            {
            }
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
    private static void generateItem(int hunterID,BufferedWriter bw,ArrayList<String> desc, ArrayList<String> colour, ArrayList<String> noun)
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
        try
        {
            bw.write("insert into Item values(" +id+","+hID+",NULL,"+name+","+itemRank+","+rarity+","+itemValue+ ");");
            generateItemDesc(bw,name,desc,rdm);
        }
        catch (Exception e)
        {
        }
        System.out.println("(" +id+","+hID+","+name+","+itemRank+","+rarity+","+itemValue+ ")");


    }
    /*
    <ITEM NAME>
    Name
    Description
     */
    private static void generateItemDesc(BufferedWriter bw, String name, ArrayList<String> desc, Random rdm) throws Exception {

        String description = desc.get(rdm.nextInt(desc.size()));

        bw.write("insert into Item_Name values(" +name+ ","+description+ ");");
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
    private static void generateHuntingMission(BufferedWriter bw,int missionHolderID,ArrayList<String> desc)
    {
        Random rdm = new Random();
        int id = rdm.nextInt(1000);
        int hunterID;
        int expReward = rdm.nextInt(1000000);
        int goldReward = rdm.nextInt(600000);
        String description = desc.get(rdm.nextInt(desc.size()));
        String deadline = "2017-08-11"; //Year-Month-Day
        String missionType;

        try{
            bw.write("insert into Hunting_missions values("+id+",NULL,"+missionHolderID+","+expReward+","+goldReward+","+description+","+deadline+",NULL,NULL,NULL,NULL);");
            generateHuntingDifficulty(bw,goldReward,expReward);
        }
        catch(Exception e)
        {

        }

    }
    private static void generateHuntingDifficulty(BufferedWriter bw, int gold, int exp)throws Exception
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

        bw.write("insert into Huntin_missions_difficulty values("+difficulty+","+exp+","+gold+");");
        System.out.println("("+difficulty+","+exp+","+gold+")");

    }
    private static void generateHuntingMonster()
    {

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
    private static void generateItemMission(int iID, int mhID,BufferedWriter bw,ArrayList<String> desc)
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
    }

    private static void generateItemDifficulty(BufferedWriter bw,int gold, int exp) throws Exception
    {
        generateHuntingDifficulty(bw, gold,exp);
    }



}

