package de.bibatwork.spring.userInterface.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Optional;

import de.bibatwork.spring.application.model.FooModel;
import de.bibatwork.spring.domain.entity.Foo;
import de.bibatwork.spring.persistence.repository.FooRepository;
import de.bibatwork.spring.userInterface.controller.modelAssembler.FooAssembler;
import jakarta.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class FooController {
    //https://www.baeldung.com/spring-rest-docs-vs-openapi

    private final FooRepository repository;

    private final FooAssembler resourceAssembler;


    public FooController(FooRepository repository, FooAssembler resourceAssembler){
        this.repository = repository;
        this.resourceAssembler = resourceAssembler;
    }


    @GetMapping
    public ResponseEntity<CollectionModel<FooModel>> getAllFoos() {
        CollectionModel<FooModel> resources =
                resourceAssembler.toCollectionModel(repository.findAll());

        if (resources.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<FooModel> getFooById(@PathVariable("id") Long id) {

        Optional<Foo> foo = repository.findById(id);
        return foo.map(value -> new ResponseEntity<>(resourceAssembler.toModel(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Foo> addFoo(@RequestBody @Valid Foo foo) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(linkTo(FooController.class).slash(foo.getId())
                .toUri());
        Foo savedFoo;
        try {
            savedFoo = repository.save(foo);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(savedFoo, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoo(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Foo> updateFoo(@PathVariable("id") long id, @RequestBody Foo foo) {
        boolean isFooPresent = repository.existsById(id);

        if (!isFooPresent) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Foo updatedFoo = repository.save(foo);

        return new ResponseEntity<>(updatedFoo, HttpStatus.OK);
    }
}
