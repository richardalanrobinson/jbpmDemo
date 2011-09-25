NOTES and GOTCHAS

PROCESS FILES

* Don't create a Drools Flow file for this. Create a regular text file and give it extension of .bpmn
* After creating a new bpmn file, go to Package Explore, select it and right click, then choose Guvnor, Add...
* After updating/editing a bpmn file, go to Package Explorer, select it and right click, then choose Guvnor, Commit
* Be sure that the packageName attribute is correct ("defaultPackage" unless otherwise)

* Important debugging step *
After you add or commit the process file to Guvnor, you should see it in the bpm-console 
(http://localhost:8080/bpm-console) under Processes. If you don't, then open guvnor 
(http://localhost:8080/drools-guvnor/), select Knowledge Bases, Packages > defaultPackage. This opens
up a tab, click on the Edit button. Then click on the buildPackage button. If there are errors,
they will display here.

FREEMARKER TEMPLATES
* Be sure the name of the freemarker file matches that of the bpmn (process) file.


To get the ftl (freemarker template files) into the bpm-console,

You need to update the jbpm-gwt-form-5.1.0.Final.jar with them. The jar is part of the 
jbpm-gwt-console-server.war.

Steps:

cd $JBOSS_HOME/server/default/deploy
jar -xvf jbpm-gwt-console-server.war jbpm-gwt-form-5.1.0.Final.jar
cd WEB-INF/lib
jar -uf jbpm-gwt-form-5.1.Final.jar -C <path/to/ftl> <name>.ftl
cd ../../
jar -uf jpm-gwt-console-server.war WEB-INF/lib/jbpm-gwt-form-5.1.0.Final.jar

If Jboss is already running, it should register the changes automatically.