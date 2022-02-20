package com.example.mygraphqldemo.service;

import com.example.mygraphqldemo.repository.BookFetcher;
import com.example.mygraphqldemo.repository.FriendFetcher;
import com.example.mygraphqldemo.repository.StocksDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class GraphQLService {
    private GraphQL graphQL;

    private final StocksDataFetcher stocksDataFetcher;
    private final BookFetcher bookFetcher;
    private final FriendFetcher friendFetcher;

    @Value("schema.graphql")
    private ClassPathResource classPathLoader;

    public GraphQLService(StocksDataFetcher stocksDataFetcher, BookFetcher bookFetcher, FriendFetcher friendFetcher) {
        this.stocksDataFetcher = stocksDataFetcher;
        this.bookFetcher = bookFetcher;
        this.friendFetcher = friendFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {

        InputStream st = classPathLoader.getInputStream();
        Reader targetReader = new InputStreamReader(st);

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(targetReader);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring.dataFetcher("stocks", stocksDataFetcher))
                .type("Query", typeWiring -> typeWiring.dataFetcher("books", bookFetcher))
                .type("Query", typeWiring -> typeWiring.dataFetcher("friend", friendFetcher))
                .build();
    }

    public GraphQL initiateGraphQL() {
        return graphQL;
    }
}
