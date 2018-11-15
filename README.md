# ConGames
ConGames is a web digital game distributor made as an assignment for the 2018 edition of the "Web Development and the Semantic Web" course, by André Barreto Silveira and Eric Marchetti Santos.

## Infrastructure Setup
ConGames was created in accordance with the Jbutler tutorial. To setup ConGames, follow the [JButtler Tutorial](https://github.com/dwws-ufes/jbutler/wiki/Tutorial00) with the following differences:

1.  Wildfly 14 is the preferable choice of server.
2.  The Schema to be created in MySql Workbench should be called `congames`.
3.  When configuring the datasource in wildfly, in `standalone.xml`, instead of the datasource mentioned in the tutorial, add the following:
  
```xml
<datasource jta="true" jndi-name="java:jboss/datasources/Congames" pool-name="Congames" enabled="true" use-java-context="true">
	<connection-url>jdbc:mysql://localhost:3306/congames?useSSL=false</connection-url>
	<driver>mysql</driver>
	<security>
		<user-name>dwws</user-name>
		<password>dwws</password>
	</security>
</datasource>
```

## Project Setup
1.  Open Eclipse with Java EE context and select File->Import->Existing Maven Project then select this repository master branch as root directory.
2.  The project `pom.xml` should be displayed. Select it, and click Finish.

## Configure ConGames with Wildfly
1.  Open the Servers view;
2.  Right-click WildFly 14 and select Add and Remove...;
3.  Using the Add button, select and move congame from the left (Available) to the right (Configured);
4.  Click Finish, then right-click WildFly 14 again and select Start;
5.  Open http://localhost:8080/congames/ in your favorite Web browser.

