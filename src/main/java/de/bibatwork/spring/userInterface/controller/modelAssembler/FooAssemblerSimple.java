package de.bibatwork.spring.userInterface.controller.modelAssembler;

import de.bibatwork.spring.domain.entity.Foo;
import de.bibatwork.spring.userInterface.controller.FooController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.SimpleIdentifiableRepresentationModelAssembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/*
Ref.:
    - https://spring.io/guides/gs/rest-hateoas/
    - https://github.com/spring-projects/spring-hateoas-examples/tree/main/hypermedia/src/main/java/org/springframework/hateoas/examples
    - https://github.com/spring-projects/spring-hateoas-examples/blob/c100c56982473c60e8042f14e6052333a3ae1b55/commons/src/main/java/org/springframework/hateoas/SimpleIdentifiableRepresentationModelAssembler.java
 */
public class FooAssemblerSimple extends SimpleIdentifiableRepresentationModelAssembler<Foo> {

    FooAssemblerSimple(){
        super(FooController.class);
    }

    /**
     * Define links to add to every {@link EntityModel}.
     *
     * @param resource
     */
    @Override
    public void addLinks(EntityModel<Foo> resource) {
        /**
         * Add some custom links to the default ones provided. NOTE: To replace default links, don't invoke
         * {@literal super.addLinks()}.
         */
        super.addLinks(resource);

        /*
        resource.getContent().getId(id -> {
                    // Add additional links
                    resource.add(linkTo(methodOn(FooController.class).getFooById(id)).withRel("detailed"));
                });

         */
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Foo>> resources) {
        super.addLinks(resources);

        resources.add(linkTo(methodOn(FooController.class).getAllFoos()).withRel("detailedFoos"));
    }
}
