# GWizard Example

This is a very very simple REST server that demonstrates how to use GWizard. You are encouraged to fork this
project as a starting point.

## Running

To run this code:

```
$ mvn install
$ java -jar target/gwizard-example-1.0-SNAPSHOT.jar test.yml
```

The most exciting endpoints are probably: 

* http://localhost:8081/fun/headers
* http://localhost:8081/swagger.json
* http://localhost:8081/swagger.yaml

(Note that test.yml changes the port from the default of 8080, just to demonstrate)

## GWizard

For more information about GWizard:
https://github.com/stickfigure/gwizard
