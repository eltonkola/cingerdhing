# cingerdhing
web app mbi spring boot qe bon gjona



docker run  -e "SPRING_PROFILES_ACTIVE=prod" -p 9000:8080 -t eltonkola/cingerdhing-docker:0.0.1-SNAPSHOT


-e "SPRING_PROFILES_ACTIVE=dev"

#debug app
$ docker run -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 8080:8080 -p 5005:5005 -t springio/gs-spring-boot-docker


#openshoft

ne openshift duhetn kaluar disa parametra, nese perdoret redhat-openjdk18-openshift

$ oc env bc/openshift-quickstarts \
-e JAVA_MAIN_CLASS=org.openshift.quickstarts.undertow.servlet.ServletServer \
-e MAVEN_ARGS="package -P flat-classpath-jar -Dcom.redhat.xpaas.repo.redhatga" \
-e ARTIFACT_COPY_ARGS="-r lib *.jar" \
-e JAVA_LIB_DIR=lib \
-e JAVA_APP_JAR=undertow-servlet.jar \
-e JAVA_APP_DIR=/deployments
