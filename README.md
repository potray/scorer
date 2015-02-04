#Scorer
A service for storing scores for games. It offers several features such as:
- Register
- Login
- Upload score

This service is currently running in an Openshift Apache server, so any client can communicate with it via JSON at this URL: http://scorer-potray.rhcloud.com/Scorer/rest/scorer.

The clients must use a set of URLs to access to the features of the service. As an example, if a client wants to register a user, it must use the URL  http://scorer-potray.rhcloud.com/Scorer/rest/scorer/register.

Here is a description of all features of the service, and how to use them via JSON:

##Add score
####URL: /addScore
#####JSON input: {"userName" : String, "game" : String, "score" : long}
#####JSON output: {"response" : String}
Example of a valid input: {"userName" : "Potray", "game" : "Simpliest Game in the World", "score" : 2}
Example of an output: {"response" : "Ok"}

This adds a score to a player. The player must be registered in the data base, so the client must assure that there is a login system.

##Register
####URL: /register
#####JSON input: {"userName" : String, "password" : String}
#####JSON output: {"response" : String}





