# Setup Environment

Location where the OSBot picks up the scripts for ubuntu ```/home/mannu/OSBot/```

Where to upload scripts
```
/home/mannu/OSBot/Scripts/
```
This is done via the pom plugin
```xml
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.3.1</version>
        <configuration>
            <outputDirectory>/home/${env.USER}/OSBot/Scripts</outputDirectory>
        </configuration>
    </plugin>
```


In order to link the OSBot's Jar
```xml
    <dependency>
        <groupId>com.osbot.offical</groupId>
        <artifactId>OSBot</artifactId>
        <version>2.5.42</version>
        <scope>system</scope>
        <systemPath>${basedir}/../lib/OSBot 2.5.42.jar</systemPath>
    </dependency>
```

