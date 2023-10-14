package de.bibatwork.spring.userInterface.controller.modelAssembler;

import de.bibatwork.spring.domain.entity.Foo;
import de.bibatwork.spring.userInterface.controller.FooController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.SimpleIdentifiableRepresentationModelAssembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
