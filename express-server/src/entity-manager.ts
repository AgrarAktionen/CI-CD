import {DatabaseSettings} from "./settings"
import {Knex, knex} from "knex"

class EntityManager {
    private _knex: Knex

    initialize(databaseSettings: DatabaseSettings) {
        const connection = {
            host : databaseSettings.host,
            user : databaseSettings.username,
            password : databaseSettings.password,
            database : databaseSettings.database
        }

        this._knex = knex({
          client: "mysql",
          connection})
    }
    createQuery<T>(table: string) {
        return this._knex<T>(table)
    }
}

export default new EntityManager()
