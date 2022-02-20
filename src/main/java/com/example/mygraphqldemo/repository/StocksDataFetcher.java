package com.example.mygraphqldemo.repository;

import com.example.mygraphqldemo.model.StockDetails;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StocksDataFetcher implements DataFetcher<List<StockDetails>> {

    @Override
    public List<StockDetails> get(DataFetchingEnvironment dataFetchingEnvironment) {
        StockDetails stockDetails = new StockDetails();
        stockDetails.setTicker("GP");
        stockDetails.setStockValue("127.10");
        stockDetails.setVolume("111.68M");
        stockDetails.setEPS("3.28");
        stockDetails.setDividend("0.82 (0.64%)");
        stockDetails.setMarketCap("2.14T");

        List<StockDetails> stockDetailsList = new ArrayList<>();
        stockDetailsList.add(stockDetails);
        return stockDetailsList;
    }
}
