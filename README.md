# Overview

This is a Spring Boot Maven archetype that allows us to automatically generate skeleton Spring Boot microservices that are ready configured for our platforms and include all our extension points.

New microservices should be created via the Maven archetype. Firstly you should ensure that you have the archetype installed locally:

`mvn clean install`

Then it is as simple as a single command to create the new microservice project (substituting in the appropriate values - INCLUDING THE LATEST VERSION OF THE ARCHETYPE), for example:

```
mvn archetype:generate \
  -DarchetypeGroupId=com.capgemini \
  -DarchetypeArtifactId=boot-microservice-archetype \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.capgemini.demo \
  -DartifactId=example-service \
  -DcontextRoot=example \
  -DmicroservicePort=8888 \
  -DmicroserviceGroupName=demo \
  -DmicroserviceName=example-service \
  -DmicroserviceUserName=example \
  -DcloudConfigEnabled=false \
  -DinteractiveMode=false
```

For the **groupId** enter your top-level Java package e.g. `com.capgemini.customers`

For the **contextRoot** enter the HTTP context root for the app.

For the **microserviceGroupName** enter the highest pacakge level e.g. for `com.capgemini.customers` use `customers`

For the **microservicePort** enter the HTTP port that the app should listen on.

For the **artifactId** and **microserviceName** (these should be the same) choose an appropriate microservice name to distinguish its logs etc.

For the **microserviceUserName** enter the Linux user under which the microservice will run and which will own the directories to which the microservice will be installed - don't confuse it with the HTTP basic auth users (that are hard coded into the archetype).

For the **cloudConfigEnabled** use true or false depending on whether you want to connect to a Spring Cloud Config Server to serve the microservice's YAML configuration.

# Before you push the changes in Version control

If you run the above command on Windows machine, the generated files will have all the end of line characters windows encoded. Before you commit the changes in the Version control please make sure you convert them all into Unix format.

You can achieve this in Notepad++ by selecting Menu -> Edit -> EOL Conversion -> UNIX Format.

To avoid this it is strongly recommended that you run the archetype from a Unix environment e.g. an Ubuntu desktop.
