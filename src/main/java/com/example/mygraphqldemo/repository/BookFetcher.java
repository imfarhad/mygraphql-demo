package com.example.mygraphqldemo.repository;

import com.example.mygraphqldemo.model.Book;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookFetcher implements DataFetcher<List<Book>> {

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {

        Book aBook = new Book();
        aBook.setName("Men's search for meaning");
        aBook.setAuthor("Victor Frankl");
        aBook.setType("Psychology");

        return List.of(aBook);
    }
}
