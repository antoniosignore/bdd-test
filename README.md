## Cucumber Integration Test

    java -Dspring.profiles.active=local             -jar sizes-1.0.0-SNAPSHOT.jar --server.port=5555 &
    java -Dspring.profiles.active=local             -jar stores-1.0.0-SNAPSHOT.jar --server.port=4445 &
    java -Dspring.profiles.active=local             -jar countries-0.0.1-SNAPSHOT.jar --server.port=7777 &
    java -Dspring.profiles.active=local             -jar sessions-1.0.0-SNAPSHOT.jar --server.port=8888 &
    java -Dspring.profiles.active=local             -jar replist-1.0.0-SNAPSHOT.jar --server.port=9999 &
    java -Dspring.profiles.active=local             -jar articles-1.0.0-SNAPSHOT.jar --server.port=4444 &
    java -Dspring.profiles.active=local             -jar orders-1.0.0-SNAPSHOT.jar --server.port=4447 &
