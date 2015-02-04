#Scorer
A service for storing scores for games. It offers several features such as:
- Register
- Login
- Upload score

This service is currently running in an Openshift Apache server, so any client can communicate with it via JSON at this URL: http://scorer-potray.rhcloud.com/Scorer/rest/scorer.

There is a client for registering at http://scorer-potray.rhcloud.com/Scorer/. Also there is a game so the service can be tested at http://danieldiazsalas.tk/simpliestgameintheworld/.

The clients must use a set of URLs to access to the features of the service. As an example, if a client wants to register a user, it must use the URL  http://scorer-potray.rhcloud.com/Scorer/rest/scorer/register.

Here is a description of all features of the service, and how to use them via JSON:

##Add score
####URL: /addScore
#####JSON input: {"userName" : String, "game" : String, "score" : long}
#####JSON output: {"response" : String}
Example of a valid input: {"userName" : "potray", "game" : "Simpliest Game in the World", "score" : 2}
Example of an output: {"response" : "Ok"}

This adds a score to a player. The player must be registered in the data base, so the client must assure that there is a login system.

##Register
####URL: /register
#####JSON input: {"userName" : String, "password" : String}
#####JSON output: {"response" : boolean}
Example of a valid input: {"userName" : "potray", "password" : "didYouReallyThinkIWouldPutAPasswordInAReadmeFile"}
Example of an output: {"response" : true}

This creates a user and returns wether the user was created. If the user wasn't created that means there is already a user with the same username.

##Login
####URL: /login
#####JSON input: {"userName" : String, "password" : String}
#####JSON output: {"response" : String}
Example of a valid input: {"userName" : "potray", "password" : "didYouReallyThinkIWouldPutAPasswordInAReadmeFile"}
Example of an output: {"response" : "Incorrect password"}

This is not a login system. It just validates the password in the JSON with the password in the database. The login system must be implemented by the client. There is an example of a login system in this repository.

There are 3 possible responses:

- "Ok"
- "Incorrect password"
- "User doesn't exist"

##Scores
####URL: /scores
#####JSON input: {"userName" : String, "game" : String}
#####JSON output: {"games" : Array of String, "scores:" Array of Score, "name" : String}
Note: a Score is parsed like: {"score" : long, "game" : String, "date" : String}

Example of a valid input: {"userName" : "Potray", "game" : "Simplest Game in the World"}
Example of a valid output: {"games":"["Simpliest Game in the World"]","scores":"[{"score":7,"game":"Simpliest Game in the World","date":"2015-02-03 18:32:00"},{"score":12,"game":"Simpliest Game in the World","date":"2015-02-04 05:57:01"}]","name":"potray"}

This just gets all scores of an user at a game. Note that the player must exists in the database and has to have a score of the game.

##Games
####URL: /games
#####JSON input: {"userName" : String}
#####JSON output: {Array of String}
Example of a valid input: {"userName" : "Potray"}
Example of a valid output: {"Simpliest Game in the World", "Other game", "And another game"}

It gets all the games a user has ever played. Note that the user must exist in the system. 

###Libraries used
Since some of this libraries have licenses I decided not to distribute a single one of them. But if anyone wants to clone this repository and use it (I recommend using Eclipse IDE since that's what I used), here is a list of all the jars I have in the WebContent/WEB-INF/lib folder.

- aopalliance-repackaged-2.4.0-b06.jar
- asm-debug-all-5.0.2.jar
- cors-filter-1.5.1.jar
- derby.jar
- eclipselink.jar
- hk2-api-2.4.0-b06.jar
- hk2-locator-2.4.0-b06.jar
- hk2-utils-2.4.0-b06.jar
- javassist-3.18.1-GA.jar
- javax.annotation-api-1.2.jar
- javax.inject-2.4.0-b06.jar
- javax.persistence_2.1.0.v201304241213.jar
- javax.servlet-api-3.0.1.jar
- javax.ws.rs-api-2.0.1.jar
- jaxb-api-2.2.7.jar
- jersey-client.jar
- jersey-common.jar
- jersey-container-servlet-core.jar
- jersey-container-servlet.jar
- jersey-guava-2.15.jar
- jersey-server.jar
- json-simple-1.1.1.jar
- org.osgi.core-4.2.0.jar
- osgi-resource-locator-1.0.1.jar
- persistence-api-1.0.jar
- validation-api-1.1.0.Final.jar