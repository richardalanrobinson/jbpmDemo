To get the ftl (freemarker template files) into the bpm-console,

you need to update the jbpm-gwt-form-5.1.0.Final.jar with them. The jar is part of the 
jbpm-gwt-console-server.war.

Steps:

cd $JBOSS_HOME/server/default/deploy
jar -xvf jbpm-gwt-console-server.war jbpm-gwt-form-5.1.0.Final.jar
cd WEB-INF/lib
jar -uf jbpm-gwt-form-5.1.Final.jar -C <path/to/ftl> <name>.ftl
cd ../../
jar -uf jpm-gwt-console-server.war WEB-INF/lib/jbpm-gwt-form-5.1.0.Final.jar

If Jboss is already running, it should register the changes automatically.