#VS

Dies ist das Readme fuer VS im WiSe 20.12.2021

#Allgemeine Hinweise zum Labor

Als Nutzer sollte der "Networker" ausgewählt werden.
Wenn man den Networker ausgewählt hat, muss man zwei mal das Passwort "praktikum" eingeben, um danach dann ein eigenes Passwort festlegen zu können.

#Labor 1

Vorweg:

1) Reporitory herunterladen und entzippen.
2) Repo in IDE öffnen.
3) Kompiler auf java 8 stellen für projekt und module

#Starten des ersten Labors

1) Terminal öffnen und reg.bat ausführen mit folgendem Command -> rmiregistry


#Weitere Infos zu Labor 1
#RMI-VM-Options
Requirements:
- RMI Registry needs to be started before the server starts
- RMI Registry version should be the same as the used sdk for compiling (IntelliJ can use different sdk version as used in terminal!)
- The definition of the executed code has to be present on the server, if file:// is used. (Not sure if its also needed to be on the server, when http:// is used. Will the code than be loaded from the public URL?).
- The codebase property is the root directory of the class files

Setting VM options in IntelliJ:
Open Run configurations, click Modify options, enable "Add VM options" and paste in VM options field.

Setting VM options in Eclipse:
Open Run configurations, click Arguments and paste in to "VM arguments:" field.

Linux:
policy example:
grant codeBase "file:/home/networker/IdeaProjects/VSPT1/out/production/VSPT1" {
    permission java.security.AllPermission;
};

Client:
-cp /home/mehkir/IdeaProjects/VSRMI/out/production/VSRMI:/home/mehkir/IdeaProjects/VSRMI/message_service.jar -Djava.rmi.server.codebase=file:///home/mehkir/IdeaProjects/VSRMI/out/production/VSRMI/ -Djava.security.policy=security.policy

Server:
-cp /home/mehkir/IdeaProjects/VSRMI/out/production/VSRMI:/home/mehkir/IdeaProjects/VSRMI/message_service.jar -Djava.rmi.server.codebase=file:///home/mehkir/IdeaProjects/VSRMI/message_service.jar -Djava.rmi.server.hostname=SERVER -Djava.security.policy=security.policy


Windows:
policy example:
grant codeBase "file:/C:/Users/abs949/eclipse-workspace/VSRMI/bin" {
    permission java.security.AllPermission;
};

Client:
-cp C:\Users\abs949\eclipse-workspace\VSRMI\bin:C:\Users\abs949\eclipse-workspace\VSRMI\message_service.jar -Djava.rmi.server.codebase=file:/C:\Users\abs949\eclipse-workspace\VSRMI\bin -Djava.security.policy=security.policy

Server:
-cp C:\Users\abs949\eclipse-workspace\VSRMI\bin:C:\Users\abs949\eclipse-workspace\VSRMI\message_service.jar -Djava.rmi.server.codebase=file:/C:\Users\abs949\eclipse-workspace\VSRMI\message_service.jar -Djava.rmi.server.hostname=SERVER -Djava.security.policy=security.policy

Can help to find the cause of problems: -Djava.security.debug=access,failure


#Enable Javafx in Linux with openjdk.txt
IntelliJ:
1. File -> Project Structure
2. Libraries
3. Click + sign -> Java and add:
   - /usr/lib/jvm/default/lib/javafx.controls.jar
   - /usr/lib/jvm/default/lib/javafx.graphics.jar
   - /usr/lib/jvm/default/lib/javafx.base.jar
   
Open Run configurations, click Modify options, enable "Add VM options" and paste in VM options field:
--module-path /usr/lib/jvm/default/lib/javafx.controls.jar:/usr/lib/jvm/default/lib/javafx.graphics.jar:/usr/lib/jvm/default/lib/javafx.base.jar --add-modules javafx.controls

Eclipse:
1. Right click on Project -> Properties -> Java Build Path
2. On the right window click Libraries
3. Click Add External JARs and add:
   - /usr/lib/jvm/default/lib/javafx.controls.jar
   - /usr/lib/jvm/default/lib/javafx.graphics.jar
   - /usr/lib/jvm/default/lib/javafx.base.jar
   
Open Run configurations, click Arguments and paste in to "VM arguments:" field:
--module-path /usr/lib/jvm/default/lib/javafx.controls.jar:/usr/lib/jvm/default/lib/javafx.graphics.jar:/usr/lib/jvm/default/lib/javafx.base.jar --add-modules javafx.controls



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

