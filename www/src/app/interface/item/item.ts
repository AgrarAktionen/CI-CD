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
    stattpreis: number;
    bruttopreis: number;

    percentage: number;
}

interface Price {
    stattpreis: number;
    bruttopreis: number;
    percentage: number;
}


