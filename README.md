# Collab-Sketchpad

A real-time collaborative canvas sketchpad for multiple users connected via socket streaming. <br/>
The `message` class handles parsing of messages for incoming and outgoing sketch data from client <br/>
The server implements synchronization through locks to make sure that no data is corrupted during simultenous editing by threads.

<img width="1200" alt="Screen Shot 2022-12-09 at 7 03 09 PM" src="https://user-images.githubusercontent.com/64368452/206713837-dfede64b-6568-4a40-9554-3077691db25a.png">

## Server

## Client

## Synchronization Locks
I used synchronized methods for those methods that access the shape TreeMap in the Sketch class. 
This is because multiple threads or many different server communicators (one for each of the client editors) will be accessing a sketch. 
With synchronized methods, if several editor communicators make method calls to access the shape list and move a shape, one is allowed to proceed and the others must wait. 

## Testing and Implementing Safety Checks
It is possible that two users might click delete on a shape at the same time. If one is executed first, the other user's delete call will not be able to find that shape with the given id and the removeShape method would throw a KeyNotFoundException. 
<br/> To prevent this, I added the check in the shape class methods to make sure that the TreeMap contains the key id before trying to delete/recolor/move the shape with that id.


