# Core Service Platform - API manager(aka Proxy)

## Application goal
In general system A request something from system B and get result. The goal of this application is to proxy requests from 
system A to B and make a possibility to build billing for such communications. 

So application performs following:
 - Proxy requests between systems with/without transformation
 
    SystemA -> data in A format -> Api manager(aka Proxy) -> data in B format -> SystemB
 - Count the amount of systems invocation, so billing based on the request amount become available 
    
## How to run locally
In order to run this application locally you should satisfy following requirements:
 - Java 11
 

## TODO
- connection factory for IBM MQ
- test producer for IBM MQ