package com.example.mygraphqldemo.controller;

import com.example.mygraphqldemo.service.GraphQLService;
import graphql.ExecutionResult;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/graphql-demo")
@RestController
public class GraphQLController {

    private final GraphQLService graphQLService;

    public GraphQLController(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @RequestMapping(value = "/graphqlData")
    public String getPreAuthDecisionData(@RequestBody String query){
        ExecutionResult execute = graphQLService.initiateGraphQL().execute(query);
        Map<String, String> obj = execute.getData();
        JSONObject jsonObject = new JSONObject(obj);
        return jsonObject.toString();
    }

}
