# GWizard Example

This is a very very simple REST server that demonstrates how to use GWizard. You are encouraged to fork this
project as a starting point.

This example targets **Gwizard 1.3.0** and requires Java 17 or newer and Maven 3.9.6 or newer.

## Running

To run this code:

```
$ mvn clean verify
$ java -jar target/gwizard-example-1.0-SNAPSHOT.jar test.yml
```

The most exciting endpoint is probably: http://localhost:8081/api/fun/headers

(Note that test.yml changes the port from the default of 8080, just to demonstrate)

## Metrics

Gwizard 1.3.0 uses Micrometer and requires an explicit `MeterRegistry` binding.
`ExampleModule` binds a singleton `SimpleMeterRegistry`, which keeps metrics in memory
and doesn't send them anywhere. Replace that binding with a publishing registry for
your preferred backend when using this example as the basis for an application.

The resource methods use `io.micrometer.core.annotation.Timed`. For example,
`ThingsResource.list()` records an `example.things.list` timer. Inject `MeterRegistry`
to inspect meters or add your own instrumentation. Gwizard registers JVM metrics on
service startup and closes the registry on shutdown.

The tests exercise both direct resource calls and real HTTP requests, including
assertions that Micrometer timers are recorded. The HTTP tests use port 8080 and an
in-memory H2 database; they close the HTTP client, services, and database after each test.

## GWizard

For more information about GWizard:
https://github.com/gwizard/gwizard
