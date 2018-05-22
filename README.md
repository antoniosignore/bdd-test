## Cucumber Integration Test

    java -Dspring.profiles.active=local             -jar sizes-1.0.0-SNAPSHOT.jar --server.port=5555 &
    java -Dspring.profiles.active=local             -jar stores-1.0.0-SNAPSHOT.jar --server.port=4445 &
    java -Dspring.profiles.active=local             -jar countries-0.0.1-SNAPSHOT.jar --server.port=7777 &
    java -Dspring.profiles.active=local             -jar sessions-1.0.0-SNAPSHOT.jar --server.port=8888 &
    java -Dspring.profiles.active=local             -jar replist-1.0.0-SNAPSHOT.jar --server.port=9999 &
    java -Dspring.profiles.active=local             -jar articles-1.0.0-SNAPSHOT.jar --server.port=4444 &
    java -Dspring.profiles.active=local             -jar orders-1.0.0-SNAPSHOT.jar --server.port=4447 &


             
Session host: "develop.session-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net     
Article host: "develop.article-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net        
List host: "develop.replist-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net       
Store host: "develop.store-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net        
Country host: "develop.country-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net        
Scale host: "develop.size-service.sam.uonr0.k8s.asgard.dub.aws.k8s.3stripes.net        


Session host: "staging.session-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net 
Article host: "staging.article-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net 
List host: "staging.replist-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net       
Store host: "staging.store-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net        
Country host: "staging.country-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net      
Scale host: "staging.size-service.sam-stg.rfki6.k8s.asgard.dub.aws.k8s.3stripes.net
