export interface Item {
    itemId : Number;
    artikelbezeichnung : string;
    artikelnummer : string;
    beschreibungsfeld : string;
    bildLink : string;
    deeplink : string;
    ean : string;
    hersteller : string;
    kategoriepfad : string;
    verfuegbarkeit : string;
    versandkosten : string;
    prices : Price;
    stattpreis: string;
    bruttopreis: string;

}

interface Price {
    stattpreis: string;
    bruttopreis: string;
    percentage: number;
}


