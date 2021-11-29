package com.aktionen.agrar.download;


import ai.djl.ModelException;
import ai.djl.translate.TranslateException;
import com.aktionen.agrar.categorize.dao.CategoriesDao;
import com.aktionen.agrar.dao.CheckSumDao;
import com.aktionen.agrar.dao.ItemDao;
import com.aktionen.agrar.dao.PredectionDao;
import com.aktionen.agrar.dao.PriceDao;
import com.aktionen.agrar.model.CheckSum;
import com.aktionen.agrar.model.Item;
import com.aktionen.agrar.model.PredictedItem;
import com.aktionen.agrar.model.Price;
import com.opencsv.bean.CsvToBeanBuilder;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.scheduler.Scheduled;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.io.IOUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

@QuarkusMain
public class CsvDownloader {

    @Inject
    ItemDao itemDao;

    @Inject
    PriceDao priceDao;

    @Inject
    PredectionDao predectionDao;

    @Inject
    CategoriesDao categoriesDao;

    @Inject
    CheckSumDao checkSumDao;

    //final String FILEURI = "file.csv";

    List<Item> items = new LinkedList<>();
    List<Price> prices = new LinkedList<>();
    List<PredictedItem> predictedItems = new LinkedList<>();
    byte[] BYTES;

    public CsvDownloader() throws IOException {
    }

    public static void main(String[] args) {
        //fetchCSV(); //uncomment to get new API CSV DATA
        Quarkus.run(args);
    }

    public List<Item> createItemList() {
        InputStream inputStream = new ByteArrayInputStream(BYTES);
        List<Item> itemList = new CsvToBeanBuilder(
                new InputStreamReader(inputStream))
                .withSeparator(';')          // custom CSV parser
                .withType(Item.class)
                .withSkipLines(1)
                .build()
                .parse();

        return itemList;
    }

    public List<Price> createPriceList() {
        InputStream inputStream = new ByteArrayInputStream(BYTES);
        List<Price> priceList = new CsvToBeanBuilder(
                new InputStreamReader(inputStream))
                .withSeparator(';')          // custom CSV parser
                .withType(Price.class)
                .withSkipLines(1)
                .build()
                .parse();

        return priceList;
    }
    public List<PredictedItem> predictedItemList() {
        InputStream inputStream = new ByteArrayInputStream(BYTES);
        List<PredictedItem> predictedItemList = new CsvToBeanBuilder(
                new InputStreamReader(inputStream))
                .withSeparator(';')          // custom CSV parser
                .withType(PredictedItem.class)
                .withSkipLines(1)
                .build()
                .parse();

        return predictedItemList;
    }

    public CheckSum fetchCSV() throws IOException, NoSuchAlgorithmException {

        //String s = "Artikelbezeichnung;Hersteller;Artikelnummer;Kategoriepfad;Beschreibungsfeld;Bild-Link;Deeplink;Verfuegbarkeit;Bruttopreis;Stattpreis;EAN;Versandkosten;\n" +
        //        "\"Jausenbrettl, 6-tlg.\";\"keine Angabe\";\"FA15\";\"Haushalt, Gesundheit>Haushalt>Zu Tisch>Bretter\";\"Ahorn, 12x23 cm\";https://www.faie.at/media/image/10/2b/10/art_pro_fo_ia_15_200x200.jpg;\"https://www.faie.at/haushalt-gesundheit/haushalt/zu-tisch/bretter/5000015/jausenbrettl-6-tlg.\";lagernd (derzeit bis zu 10 Werktage Lieferzeit);11,91;12,95;\"9002532994148\"; 9,95;\n" +
        //        "\"Lattenhammer mit Fiberglasstiel\";\"Erba\";\"FA39\";\"Werkzeuge Maschinen>Handwerkzeug>Zangen,Zwingen,Schlagwerkzeug>Schlagwerkzeuge, Feilen>Hämmer\";\"Praktischer Hammer mit starkem Magnet und Topp-Verarbeitung.\";https://www.faie.at/media/image/22/df/16/art_pro_fo_bd_39_v2_200x200.jpg;\"https://www.faie.at/werkzeuge-maschinen/handwerkzeug/zangen-zwingen-schlagwerkzeug/schlagwerkzeuge-feilen/haemmer/5000039/lattenhammer-mit-fiberglasstiel\";lagernd (derzeit bis zu 10 Werktage Lieferzeit);8,49;9,99;\"9003324340655\"; 9,95;\n";

       // InputStream inputStream = new ByteArrayInputStream(s.getBytes());

        InputStream inputStream = new URL("https://www.faie.at/backend/export/index/agraraktionen.csv?feedID=68&hash=1bfdc5718d84ebfd191e9ee6617a7764").openStream();

        //FileInputStream fis = new FileInputStream("changefile.csv");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        String checkSum = checkSum(bytes);

        CheckSum sum = new CheckSum();
        sum.setCheckSum(checkSum);
        sum.setChanged(true);
        sum.setCsvFile(bytes);

        return sum;

    }
    private static String checkSum(byte[] bytes) throws NoSuchAlgorithmException, IOException {
        //Create checksum for this file
        //File file = new File("changefile.csv");
        //Use MD5 algorithm
        MessageDigest md5Digest = MessageDigest.getInstance("MD5");

        //Get the checksum
        String checksum = getFileChecksum(md5Digest, bytes);

        //see checksum
        System.out.println(checksum);

        return checksum;

    }
    private static String getFileChecksum(MessageDigest digest, byte[] bytearray) throws IOException
    {
        //Get file input stream for reading the file content
        //FileInputStream fis = new FileInputStream(file);
        InputStream inputStream = new ByteArrayInputStream(bytearray);
        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = inputStream.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        inputStream.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //return complete hash
        return sb.toString();
    }



    @Scheduled(every = "5s", delayed = "120s")
    @Transactional
    public void process() throws IOException, TranslateException, ImageReadException, ModelException {

        Item item = firstItemElement();
        Price price = firstPriceElement();
        PredictedItem predictedItem = firstPredictedItem();
        itemDao.insert(item, "Faie");
        //priceDao.insertAll(price, item);
        predectionDao.insertAll(predictedItem, item);
        item.setPrimeCategory(categoriesDao.selectPrime(item));
        item.setSecondCategory(categoriesDao.selectSecond(item));
        item.setThirdCategory(categoriesDao.selectThird(item));
        item.setFourthCategory(categoriesDao.selectFourth(item));
        deleteFirstElement();

    }

    @Scheduled(every = "1000s") //100s are only for test purpose, it should be reloaded once a day in reality
    @Transactional
    public void csv() throws IOException, NoSuchAlgorithmException {
        System.out.println("Downloading CSV...");
        //checkSumDao.insertCheckSum(fetchCSV());
        //downloadCSV();
        CheckSum currentCheckSum = fetchCSV();
        BYTES = checkSumDao.insertCheckSum(currentCheckSum);
        //toCSV(toImport);

    }
/*
    private void downloadCSV() throws IOException {

        InputStream inputStream = new URL("https://www.faie.at/backend/export/index/agraraktionen.csv?feedID=68&hash=1bfdc5718d84ebfd191e9ee6617a7764").openStream();
        FileOutputStream fileOS = new FileOutputStream("changefile.csv");
        IOUtils.copy(inputStream, fileOS);


        FileOutputStream fileOS = new FileOutputStream("changefile.csv");
        String s = "Artikelbezeichnung;Hersteller;Artikelnummer;Kategoriepfad;Beschreibungsfeld;Bild-Link;Deeplink;Verfuegbarkeit;Bruttopreis;Stattpreis;EAN;Versandkosten;\n" +
                "\"Jausenbrettl, 6-tlg.\";\"keine Angabe\";\"FA15\";\"Haushalt, Gesundheit>Haushalt>Zu Tisch>Bretter\";\"Ahorn, 12x23 cm\";https://www.faie.at/media/image/10/2b/10/art_pro_fo_ia_15_200x200.jpg;\"https://www.faie.at/haushalt-gesundheit/haushalt/zu-tisch/bretter/5000015/jausenbrettl-6-tlg.\";lagernd (derzeit bis zu 10 Werktage Lieferzeit);11,91;12,95;\"9002532994148\"; 9,95;\n" +
                "\"Lattenhammer mit Fiberglasstiel\";\"Erba\";\"FA39\";\"Werkzeuge Maschinen>Handwerkzeug>Zangen,Zwingen,Schlagwerkzeug>Schlagwerkzeuge, Feilen>Hämmer\";\"Praktischer Hammer mit starkem Magnet und Topp-Verarbeitung.\";https://www.faie.at/media/image/22/df/16/art_pro_fo_bd_39_v2_200x200.jpg;\"https://www.faie.at/werkzeuge-maschinen/handwerkzeug/zangen-zwingen-schlagwerkzeug/schlagwerkzeuge-feilen/haemmer/5000039/lattenhammer-mit-fiberglasstiel\";lagernd (derzeit bis zu 10 Werktage Lieferzeit);8,49;9,99;\"9003324340655\"; 9,95;\n";

        InputStream inputStream = new ByteArrayInputStream(s.getBytes());
        IOUtils.copy(inputStream, fileOS);


    }

 */

    private void deleteFirstElement() {
        items.remove(0);
        prices.remove(0);
    }

    private Item firstItemElement() throws FileNotFoundException {
        if(items.isEmpty()) {
            items = createItemList();
        }
        Item item = items.get(0);

        return item;
    }

    private Price firstPriceElement() throws FileNotFoundException {
        if(prices.isEmpty()) {
            prices = createPriceList();
        }
        Price price = prices.get(0);
        return price;
    }

    private PredictedItem firstPredictedItem() throws FileNotFoundException {
        if(predictedItems.isEmpty()){
            predictedItems = predictedItemList();
        }
        PredictedItem predictedItem = predictedItems.get(0);
        return predictedItem;
    }
}

// /var/lib/appsrvstorage/file.csv
