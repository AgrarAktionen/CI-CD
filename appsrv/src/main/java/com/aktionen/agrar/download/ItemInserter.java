package com.aktionen.agrar.download;

import com.aktionen.agrar.dao.ItemDao;
import com.aktionen.agrar.dao.PriceDao;
import com.aktionen.agrar.model.Item;
import com.aktionen.agrar.model.Price;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
@Transactional
public class ItemInserter {

    @Inject
    CsvDownloader csvDownloader;

    @Inject
    ItemDao itemDao;

    @Inject
    PriceDao priceDao;


    @Scheduled(every="60m")
    public void insertData() throws IOException {
        csvDownloader.fetchCSV();

        //-----------------ItemInsert--------------------///
        List<Item> items = csvDownloader.createItemList();
        itemDao.insertAll(items, "Faie");

        //-----------------PriceInsert--------------------///

        List<Price> prices = csvDownloader.createPriceList();
        priceDao.insertAll(prices);
    }

}
