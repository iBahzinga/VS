#VS

Dies ist das Readme fuer VS im WiSe 20.12.2021

#Allgemeine Hinweise zum Labor

Als Nutzer sollte der "Networker" ausgewählt werden.
Wenn man den Networker ausgewählt hat, muss man zwei mal das Passwort "praktikum" eingeben, um danach dann ein eigenes Passwort festlegen zu können.

#Labor 1

Zuerst das Reporitory Clonen oder als Zip herunterladen und entzippen.
Danach sollte in der IDE das ganze Projekt geöffnet werden.

Hierbei sollte dann erstmal der Kompiler auf Java 8 gestellt werden.  
Es kann vorkommen das die IDE im nachgang noch meckert, dann sollte man noch mal in den modulen schauen das auch dort entsprechend Java 8 vorbelegt ist. Wenn nicht sollte noch einmal in den Modulsettings (in der Project structure) und in den Projekteistellungen Java 8 als Basic version ausgewählt werden.
Danach sollte sich das projekt kompillieren lassen.


#Starten des ersten Labors

Terminal öffnen wo reg.bat File liegt und RMI Schnittstelle mithilfe des befehls "rmiregistry" starten.
Danach kann der Code ausgeführt werden (in GUI start liegt die main)


#Labor 2

Im folgenden kommen die Eingaben um die Actoren, Clienten und den Server zu starten

port = gibt der Server wenn man ihn startet.

anzahlWorker = 3

1)  Server

    ./vslab -m server
    
    
    
2)  Worker

    ./vslab -m worker -p $port -w $anzahlWorker
    
3)  Clienten

    ./vslab -m client -p $port 
    
    
Zum Testen werden die Folgenden Zahlen verwendet.

Z1 = 8806715679 = 3 * 29 * 29 * 71 * 211 * 233

Z2 = 9398726230209357241 = 443 * 503 * 997 * 1511 * 3541 * 7907

Z3 = 1137047281562824484226171575219374004320812483047

Z4 = 1000602106143806596478722974273666950903906112131794745457338659266842446985
022076792112309173975243506969710503
    
Wenn man den Clienten am Ende startet, kann man danach dann eine Zahl eingeben, die dann berechnet werden soll.

#Labor 3

