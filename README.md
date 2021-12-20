#VS

Dies ist das Readme fuer VS im WiSe 20.12.2021

#Labor 1
%

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
    
    
Wenn man den Clienten am Ende startet, kann man danach dann eine Zahl eingeben, die dann berechnet werden soll.

#Labor 3
