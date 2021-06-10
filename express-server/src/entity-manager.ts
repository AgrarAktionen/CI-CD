import {DatabaseSettings} from "./settings"
import {Knex} from "knex"

class EntityManager {
    private _knex: Knex;
    initialize(databaseSettings: DatabaseSettings) {
        const connection: Knex.StaticConnectionConfig = {
            host : databaseSettings.host,
            user : databaseSettings.username,
            password : databaseSettings.password,
            database : databaseSettings.database
          }

          //this._knex = Knex.(connection)
        /*
        knex = require('knex')({
            client: 'mysql',
            connection: {
              host : databaseSettings.host,
              user : databaseSettings.username,
              password : databaseSettings.password,
              database : databaseSettings.database
            }
        })
        */        
    }
}
function databaseSetup(host: string) {

}
