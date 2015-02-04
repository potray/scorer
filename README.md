#Scorer
A service for storing scores for games. It offers several features such as:
- Register
- Login
- Upload score

This service is currently running in an Openshift Apache server, so any client can communicate with it via JSON at this URL: http://scorer-potray.rhcloud.com/Scorer/rest/scorer.

The clients must use a set of URLs to access to the features of the service. As an example, if a client wants to register a user, it must use the URL  http://scorer-potray.rhcloud.com/Scorer/rest/scorer/register.

##Add score
####URL: /addScore





