# DroneClient
A program that runs on the raspberry pi of the drone itself. Sends images to and receives commands from DroneServer.

# Usage
Run the jar file as follows:

`java -jar DroneClient.jar [ClientConnectionPort] [MainServerHostname] [MainServerPort]`

Example: `java -jar DroneClient.jar 40023 192.168.0.1 40021`

Make sure that DroneServer is running before starting up DroneClient.

