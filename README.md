# Simple socket 

## Classes

**Server**

*Waiting for Client and Organizer request*

**Client**

*Get candidates and vote*

**Organizer**

*Get vote counting*

## Run application in command line

**First you need to compile files, i used javac to do that**

- javac Server.java
- javac Client.java
- javac Organizer.java
	
**Now, you can run application (OBS: you need start the server first or you get error)**

- java Server *port*
- java Client *Server ip* *Server port* *What to do (Listar (List))*
- java Client *Server ip* *Server port* *What to do (Votar (vote))* *candidate number*
- java Organizer *Server ip* *Server port* *What to do (Parcial (get vote counting))*
