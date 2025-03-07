### Simple Insurance Quotes Aggregator
This demo web app designed with `ports and adapters(hexagonal) architecture` and `DDD` in mind.     
Technology stack includes:
- Java 17
- Spring Web MVC
- Spring Cache abstraction
- Spring data JPA
- H2 
- Lombok
- Mapstruct

#### Caching
Cache mechanism implemented using `Spring Cache abstraction` and ConcurrentHashMap as cache storage.    
Spring Cache abstraction functions similarly to spring *@Transactional*, that is using `AOP` and `proxy design pattern` by default.    
Caching requires a key/value storage(e.g. redis, ehCache, ...) to populate the cache.    
Cache key is generated by appending **cache name** to *key* generated by KeyGenerator(default is *SimpleKeyGenerator*).    
Before the target method execution, cache key is calculated based on method parameters,    
then if key exists in cache storage, the corresponding value from cache is returned without method execution.    
if not, method is executed and the return type is put in the cache storage.    

#### How to run    
Set JDK 17 bin directory to `PATH` environment variable.   
and execute the following command at the root directory of the project.     
```shell
mvn spring-boot:run
```
Insurance providers which have been seeded in database are:
- id:1 Dubai National Insurance
- id:2 Germany National Insurance
- id:3 Third Party Insurance

Add some quotes to insurance providers
```shell
 curl -i -X POST --data '{"providerId":1,"price":2000,"coverageType":"CAR","policyLimit":20000}' -H 'Content-Type:application/json' http://localhost:8080/v1/quotes
```
To aggregate quotes, three parameters are added by default, you can change them by providing corresponding *request param*.   
- sortBy="price"
- size=10
- page=0

For example to change *sortBy* parameter: 
```shell
curl -i http://localhost:8080/v1/quotes/aggregate?sortBy=policylimit
```