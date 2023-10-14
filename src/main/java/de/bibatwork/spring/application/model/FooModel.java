package de.bibatwork.spring.application.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.bibatwork.spring.domain.entity.Foo;
import lombok.Getter;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import java.util.Arrays;

public class FooModel extends EntityModel<Foo>{

    public FooModel(Foo content, Link... links){
        super(content, Arrays.asList(links));
    }
}
