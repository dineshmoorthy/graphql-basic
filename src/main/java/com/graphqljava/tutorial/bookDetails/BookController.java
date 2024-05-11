package com.graphqljava.tutorial.bookDetails;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
class BookController {


    @QueryMapping
    public Mono<String> customerName(){return Mono.just("TEST Success");}
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }

    @QueryMapping(name= "clientAddressList")
    public Flux<ClientAddress> clientAddressList(){
        var authorDetails = new ClientAddress();
        authorDetails.primaryAddress ="1st cross main road";
        authorDetails.secondaryAddress = "19th block";
        var authorDetails1 = new ClientAddress();
        authorDetails1.primaryAddress ="21st cross main road";
        authorDetails1.secondaryAddress = "25th block";
        return  Flux.just(authorDetails,authorDetails1);
    }

    @QueryMapping(name= "clientDetails")
    public Mono<ClientDetails> clientDetails(@Argument String name){
        var details = new ClientDetails();
        details.id = UUID.randomUUID();
        details.name = "test";
        details.emailAddress = "test@emailid.com";
        return Mono.just(details);
    }


    @QueryMapping(name= "clientAddress")
    public Mono<ClientAddress> clientAddress(@Argument String primaryAddress){
        var details = new ClientAddress();
        details.primaryAddress ="one street";
        details.secondaryAddress = "los angeles";
        return Mono.just(details);
    }
}