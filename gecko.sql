 DROP TABLE "TEAM" CASCADE CONSTRAINTS;
 DROP TABLE "HUNTER_EXPERIENCE" CASCADE CONSTRAINTS;
 DROP TABLE "HUNTER" CASCADE CONSTRAINTS;
 DROP TABLE "MONSTER" CASCADE CONSTRAINTS;
 DROP TABLE "TEAM_CAPTAIN" CASCADE CONSTRAINTS;
 DROP TABLE "MISSIONHOLDER_REPUTATION" CASCADE CONSTRAINTS;
 DROP TABLE "MISSIONHOLDER" CASCADE CONSTRAINTS;
 DROP TABLE "ITEM" CASCADE CONSTRAINTS;
 DROP TABLE "ITEM_NAME" CASCADE CONSTRAINTS;
 DROP TABLE "HUNTING_MISSIONS" CASCADE CONSTRAINTS;
 DROP TABLE "HUNTING_MISSIONS_DIFFICULTY" CASCADE CONSTRAINTS;
 DROP TABLE "MMISSION_HAS_MONSTER" CASCADE CONSTRAINTS;
 DROP TABLE "ITEM_FORAGING_MISSION" CASCADE CONSTRAINTS;
 DROP TABLE "ITEM_MISSION_DIFFICULTY" CASCADE CONSTRAINTS;
 DROP TABLE "IMISSION_HAS_ITEM" CASCADE CONSTRAINTS;

  CREATE TABLE Team(
  teamName VARCHAR(26),
  teamRank CHAR(1),
  hunterID INTEGER NOT NULL,
  PRIMARY KEY(teamName));

  CREATE TABLE Hunter_experience(
    experience INTEGER,
    hunterLevel INTEGER,
    PRIMARY KEY(experience));

CREATE TABLE Hunter(
  hunterID INTEGER,
  name VARCHAR(26),
  age INTEGER,
  gender CHAR(1),
  experience INTEGER,
  goldBalance INTEGER,
  teamName VARCHAR(26),
  PRIMARY KEY(hunterID),
  FOREIGN KEY (experience) REFERENCES Hunter_experience(experience),
  FOREIGN KEY(teamName) REFERENCES Team(teamName));

CREATE TABLE Monster(
    monsterID INTEGER,
    name VARCHAR(30),
    location CHAR(26),
    ferocity INTEGER,
    remaining INTEGER,
    domesticated CHAR(1),
    PRIMARY KEY(monsterID));

CREATE TABLE Team_Captain(
  hunterID INTEGER,
  teamName VARCHAR(26) NOT NULL,
  PRIMARY KEY(hunterID),
  FOREIGN KEY(hunterID) REFERENCES Hunter(hunterID),
  FOREIGN KEY(teamName) REFERENCES Team(teamName));

  CREATE TABLE MissionHolder_reputation(
    goldBalance INTEGER,
    reputation CHAR(1),
    PRIMARY KEY(goldBalance));

  CREATE TABLE MissionHolder(
            missionHolderID INTEGER,
            age INTEGER,
            name VARCHAR(26),
            goldBalance INTEGER,
			PRIMARY KEY(missionHolderID),
    FOREIGN KEY (goldBalance) REFERENCES MissionHolder_reputation(goldBalance));

  
  CREATE TABLE Item(
            itemID INTEGER, 
            hunterID INTEGER, 
            missionID INTEGER,
            itemName CHAR(26), 
            itemRank CHAR(1), 
            rarity INTEGER, 
            itemValue INTEGER,
            PRIMARY KEY(itemID),
            FOREIGN KEY(hunterID) REFERENCES Hunter(hunterID));
			            
CREATE TABLE Item_Name(
            itemName VARCHAR(26), 
            description VARCHAR(512),
			PRIMARY KEY(itemName));


CREATE TABLE Hunting_missions(
            huntingMissionID INTEGER,
            hunterID INTEGER,
            teamName VARCHAR(26),
            missionHolderID INTEGER NOT NULL,
            expReward INTEGER,
            goldReward INTEGER,
            description VARCHAR(512),
            deadline CHAR(20), 
            startTime CHAR(20),            
            acceptTime CHAR(20), 
            completionTime CHAR(20), 
            forfeitTime CHAR(20),
			PRIMARY KEY (huntingMissionID),
			FOREIGN KEY(hunterID) REFERENCES Hunter(hunterID),
      FOREIGN KEY (teamName) REFERENCES Team(teamName),
      FOREIGN KEY(missionHolderID) REFERENCES MissionHolder(missionHolderID));

CREATE TABLE Hunting_missions_difficulty(
            difficulty CHAR(1),
            expReward INTEGER, 
            goldValue INTEGER,
			PRIMARY KEY (expReward,goldValue));
			
CREATE TABLE MMission_has_Monster(
            huntingMissionId INTEGER, 
            monsterid INTEGER,
			PRIMARY KEY(huntingMissionId, monsterID),
			FOREIGN KEY(huntingMissionId) REFERENCES Hunting_missions(huntingMissionId),
			FOREIGN KEY(monsterID) REFERENCES Monster(monsterID));


CREATE TABLE Item_Foraging_Mission(
            itemMissionId INTEGER,
            hunterID INTEGER,
            teamName VARCHAR(26),
            missionHolderID INTEGER NOT NULL,
            deadline CHAR(20),
            description VARCHAR(512),            
            expReward INTEGER, 
            goldReward INTEGER,
            startTime CHAR(20),  
            acceptTime CHAR(20), 
            completeTime CHAR(20), 
            forefeitTime CHAR(20),
			PRIMARY KEY (itemMissionId),
            FOREIGN KEY(hunterID) REFERENCES Hunter(hunterID),
            FOREIGN KEY (teamName) REFERENCES Team(teamName),
            FOREIGN KEY(missionHolderID) REFERENCES MissionHolder(missionHolderID));

CREATE TABLE Item_Mission_Difficulty(
            difficulty CHAR(1), 
            expReward INTEGER, 
            goldReward INTEGER,
            PRIMARY KEY (expReward,goldReward));

CREATE TABLE IMission_has_Item(
            itemMissionId INTEGER, 
            ItemId INTEGER,
			PRIMARY KEY(itemMissionId,ItemId),
			FOREIGN KEY(itemMissionId) REFERENCES Item_Foraging_Mission(itemMissionId),
			FOREIGN KEY(ItemId) REFERENCES Item(itemId));



