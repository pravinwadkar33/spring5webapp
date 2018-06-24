package com.pravin.springframework.spring5webapp.bootstrap;

import com.pravin.springframework.spring5webapp.model.Author;
import com.pravin.springframework.spring5webapp.model.Book;
import com.pravin.springframework.spring5webapp.model.Publisher;
import com.pravin.springframework.spring5webapp.repositories.AuthorRepository;
import com.pravin.springframework.spring5webapp.repositories.BookRepository;
import com.pravin.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData()
    {
        Publisher harper=new Publisher("Harper Collins","US");
        publisherRepository.save(harper);

        Publisher worx=new Publisher("Worx","UK");
        publisherRepository.save(worx);

        Author eric=new Author("Eric","Evans");
        Book ddd=new Book("Domain Driven Design","123",harper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod=new Author("Rod","Johnson");
        Book noEJB=new Book("J2EE Development with EJB","23444",worx);
        rod.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
