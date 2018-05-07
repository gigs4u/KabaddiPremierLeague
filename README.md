# My Kabaddi Premier League 

# ======== Problem Statement =======
Build a RESTful service that generates the round robin schedule for the PRO-Kabaddi event that conforms to the following constraints
- Accept N number of teams
- Each team must play against every other team once home and away
- Maximum 2 matches per day are allowed
- No team should play on consecutive days

# ======== Solution =======

# Step-1: Deploy & Run  Kabaddi Premier League Spring Boot application 

- Clone GIT Repository locally using this URL : https://github.com/gigs4u/ShoppingCartBillUtility
- Option 1: Import Maven Project in eclipse. Right click on project and Run as Java Application.
- Option 2: Open command Prompt and run "mvn package" command in the folder where you have cloned the GIT repository.
	Once mvn package is completed open the folder ..\KabaddiPremierLeague\target\ and run 
	java -jar kabaddiPremierLeague-0.0.1-SNAPSHOT.jar

- Open browser and type this URL to see Greetings REST services exposed : http://localhost:8080/?name=Paresh
Prerequisite Java 1.8 & Maven/Eclipse
# Step-2: Create a League 
Open browser Type the below URL to Create a PreamierLeague

http://localhost:8080/league?name=PremierLeague&startDate=20200218

Response - {"leagueId":1525697069537,"name":"PremierLeague","scheduleStartDate":"2020-02-17","participatingTeams":[]}

# Step-2: Register Team To League
You can register multiple list. Below is POST Method you need a rest client. Note you would have to replace LeagueId=1525697069537 created in Step 2

http://localhost:8080/league/1525697069537/registerTeamToLeague

Request- [{"name":"TeamABC","city":"Pune"},{"name":"TeamXYZ","city":"Mumbai"},{"name":"TeamABCD","city":"Pune"},{"name":"TeamXYZA","city":"Mumbai"}]

Response -
{
  "leagueId": 1525697069537,
  "name": "ProKabaddiLeague",
  "scheduleStartDate": "2020-02-17",
  "participatingTeams": [
    {
      "id": 6,
      "name": "TeamABC",
      "city": "Pune"
    },
    {
      "id": 7,
      "name": "TeamXYZ",
      "city": "Mumbai"
    },
    {
      "id": 8,
      "name": "TeamABCD",
      "city": "Pune"
    },
    {
      "id": 9,
      "name": "TeamXYZA",
      "city": "Mumbai"
    }
  ]
}
# Step-2: Generate match schedule
Note you would have to replace LeagueId=1525697069537 created in Step 2
Open the Browser and click the below URL
http://localhost:8080/league/1525697069537/schedule

[{"teamA":{"id":7,"name":"TeamXYZ","city":"Mumbai"},"teamB":{"id":9,"name":"TeamXYZA","city":"Mumbai"},"location":"Mumbai","date":"2020-02-19"},{"teamA":{"id":7,"name":"TeamXYZ","city":"Mumbai"},"teamB":{"id":8,"name":"TeamABCD","city":"Pune"},"location":"Mumbai","date":"2020-02-21"},{"teamA":{"id":9,"name":"TeamXYZA","city":"Mumbai"},"teamB":{"id":6,"name":"TeamABC","city":"Pune"},"location":"Mumbai","date":"2020-02-21"},{"teamA":{"id":8,"name":"TeamABCD","city":"Pune"},"teamB":{"id":9,"name":"TeamXYZA","city":"Mumbai"},"location":"Pune","date":"2020-02-17"},{"teamA":{"id":8,"name":"TeamABCD","city":"Pune"},"teamB":{"id":6,"name":"TeamABC","city":"Pune"},"location":"Pune","date":"2020-02-19"},{"teamA":{"id":9,"name":"TeamXYZA","city":"Mumbai"},"teamB":{"id":8,"name":"TeamABCD","city":"Pune"},"location":"Mumbai","date":"2020-02-27"},{"teamA":{"id":8,"name":"TeamABCD","city":"Pune"},"teamB":{"id":7,"name":"TeamXYZ","city":"Mumbai"},"location":"Pune","date":"2020-02-25"},{"teamA":{"id":6,"name":"TeamABC","city":"Pune"},"teamB":{"id":7,"name":"TeamXYZ","city":"Mumbai"},"location":"Pune","date":"2020-02-17"},{"teamA":{"id":7,"name":"TeamXYZ","city":"Mumbai"},"teamB":{"id":6,"name":"TeamABC","city":"Pune"},"location":"Mumbai","date":"2020-02-27"},{"teamA":{"id":6,"name":"TeamABC","city":"Pune"},"teamB":{"id":9,"name":"TeamXYZA","city":"Mumbai"},"location":"Pune","date":"2020-02-25"},{"teamA":{"id":9,"name":"TeamXYZA","city":"Mumbai"},"teamB":{"id":7,"name":"TeamXYZ","city":"Mumbai"},"location":"Mumbai","date":"2020-02-23"},{"teamA":{"id":6,"name":"TeamABC","city":"Pune"},"teamB":{"id":8,"name":"TeamABCD","city":"Pune"},"location":"Pune","date":"2020-02-23"}]

