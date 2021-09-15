package com.aktionen.agrar.download;

import com.aktionen.agrar.dao.ItemDao;
import com.aktionen.agrar.model.Item;
import com.aktionen.agrar.model.Price;
import com.opencsv.bean.CsvToBeanBuilder;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.List;
@QuarkusMain
public class CsvDownloader {

    ItemDao itemDao = new ItemDao();
    String fileName = "file.csv";

    public static void main(String[] args) throws IOException {
        //fetchCSV(); //uncomment to get new API CSV DATA
        Quarkus.run(args);
    }

    public List<Item> createItemList() throws FileNotFoundException {
        List<Item> itemList = new CsvToBeanBuilder(
                new FileReader(fileName))
                .withSeparator(';')          // custom CSV parser
                .withType(Item.class)
                .withSkipLines(1)
                .build()
                .parse();

        return itemList;
    }

    public List<Price> createPriceList() throws FileNotFoundException {
        List<Price> priceList = new CsvToBeanBuilder(
                new FileReader(fileName))
                .withSeparator(';')          // custom CSV parser
                .withType(Price.class)
                .withSkipLines(1)
                .build()
                .parse();

        return priceList;
    }


    public void fetchCSV() throws IOException {
        InputStream inputStream = new URL("https://www.faie.at/backend/export/index/agraraktionen.csv?feedID=68&hash=1bfdc5718d84ebfd191e9ee6617a7764").openStream();
        FileOutputStream fileOS = new FileOutputStream(fileName);
        int i = IOUtils.copy(inputStream, fileOS);

    }
}
