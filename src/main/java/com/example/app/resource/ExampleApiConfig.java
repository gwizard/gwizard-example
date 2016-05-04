package com.example.app.resource;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@SwaggerDefinition(
    info = @Info(
      description = "Example Gwizard App",
      version = "V1.0.1",
      title = "The GWizard Example API",
      termsOfService = "<http://localhost:8081/terms.html>",
      contact = @Contact(
            name = "Gandalf", 
            email = "into.shadow@middleearth.org", 
            url = "<http://middleearth.org>"
      ),
      license = @License(
            name = "Apache 2.0", 
            url = "<http://www.apache.org/licenses/LICENSE-2.0>"
      )
    ),
    consumes = {"application/json", "application/xml"},
    produces = {"application/json", "application/xml"},
    schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
    tags = {
            @Tag(name = "Private", description = "Tag used to denote operations as private")
    }, 
    externalDocs = @ExternalDocs(value = "Rings", url = "<http://ringsapi.io/middleearth.html>")
)
public interface ExampleApiConfig { /* empty */ }