# SoftenerAlert
A simple program I wrote up to send an email when my (extremely old) water softener runs it's 
regeneration cycle. It runs on a standard Raspberry PI and interacts through a single GPIO pin and ground.
The connection to the softener is a bit rudimentary as I tried to accomplish the project with things 
I had laying around. The softener itself uses a rotating camshaft to depress contacts in a specific order,
thus moving through the specific cycles. My non-permanent setup uses a piece of tin foil and electrical tape wrapped
around an empty part of the camshaft to expose a small section of foil. This connects two wires which send 
a signal to the GPIO pin when the cycle begins. The basic idea is very similar to a potentiometer, 
minus the resistive elements.

The program is split into two separate parts. The python file 'listener.py' acts as an event source for the Java
program and listens for a signal from the GPIO pin. This script sends a HTTP POST request to the java listener notifying it 
that the cycle has begun. After the cycle has finished the script resets an internal counter that prevent duplicate emails 
from being sent. 

The Java part uses SpringBoot to accept HTTP POST requests and send an email when it receives one. I used these two 
separate parts to allow for easy expansion if I decide to add more functionality down the line. I also wanted to learn
more about SpringBoot.




#### Resources

https://spring.io/guides

https://www.baeldung.com/

https://mkyong.com/

https://www.raspberrypi.org/documentation/
