package com.example.mygraphqldemo.repository;

import com.example.mygraphqldemo.model.Friend;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendFetcher implements DataFetcher<List<Friend>> {

    @Override
    public List<Friend> get(DataFetchingEnvironment dataFetchingEnvironment) {
        Friend aFriend = new Friend();
        aFriend.setName("Akash");
        aFriend.setAddress("Ramna, Dhaka");
        aFriend.setAge(32);
        return List.of(aFriend);
    }
}
