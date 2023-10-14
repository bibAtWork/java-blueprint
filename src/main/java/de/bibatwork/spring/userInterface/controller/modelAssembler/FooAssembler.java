package de.bibatwork.spring.userInterface.controller.modelAssembler;

import de.bibatwork.spring.application.model.FooModel;
import de.bibatwork.spring.domain.entity.Foo;
import de.bibatwork.spring.userInterface.controller.FooController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
Ref.:
        - https://github.com/BerkleyTechnologyServices/restdocs-spec-example
*/
@Component
public class FooAssembler extends RepresentationModelAssemblerSupport<Foo, FooModel> {

    public FooAssembler() {
        super(FooController.class, FooModel.class);
    }

    @Override
    public FooModel toModel(Foo foo){
        FooModel resource = new FooModel(foo);
        resource.add(linkTo(methodOn(FooController.class).getFooById(foo.getId())).withSelfRel());

        return resource;
    }


}
