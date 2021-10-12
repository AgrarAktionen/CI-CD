package com.aktionen.agrar.download;

import com.aktionen.agrar.dao.ItemDao;
import com.aktionen.agrar.dao.PriceDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ItemInserter {

    @Inject
    CsvDownloader csvDownloader;

    @Inject
    ItemDao itemDao;

    @Inject
    PriceDao priceDao;
    

/*
    @Scheduled(every="100s")
    public void insertData() throws IOException {
        csvDownloader.fetchCSV();
        List<Item> items = csvDownloader.createItemList();
        List<Price> prices = csvDownloader.createPriceList();

        //-----------------ItemInsert--------------------///
        itemDao.insertAll(items, "Faie");

        //-----------------PriceInsert--------------------///

        priceDao.insertAll(prices);
    }



    @Inject
    UserTransaction userTransaction;

    @PostConstruct
    public void init() throws SystemException {
        //set a timeout as high as you need
        userTransaction.setTransactionTimeout(3600);
    }

    @Transactional
    @Scheduled(every = "1000s")
    public void process() throws IOException, TranslateException, ImageReadException, ModelException {
        //csvDownloader.fetchCSV();
        List<Item> items = csvDownloader.createItemList();
        List<Price> prices = csvDownloader.createPriceList();
        List<PredictedItem> predictedItemList= csvDownloader.createPredictedItemList();
        itemDao.insertAll(items, "Faie");
        //itemDao.insert(items, "Faie");
        priceDao.insertAll(prices);
        predectionDao.insertAll(predictedItemList);

    }
 */

}
