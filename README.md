# cas-deployer

gradle-war-deployer for CAS since version 7.1

Before deploying, make sure that `build.properties` and `env.sh` are correctly configured.

Make sure to load env variables : `source env.sh`

Gradle commands :
- Install tomcat : `./gradlew tomcatInstall`
- Deploy webapps : `./gradlew tomcatDeploy`
- Start tomcat : `./gradlew tomcatStart`
- Stop tomcat : `./gradlew tomcatStop`
