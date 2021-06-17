import { Command } from 'commander'
import * as fs from "fs"
import { load } from "js-yaml"

export interface DatabaseSettings {
    host: string,
    username: string,
    password: string
    database: string
}

export interface Settings {
    database: DatabaseSettings
    port: number
}

export function loadSettings() {
    const program = new Command()
    program
        .version("0.0.1")
        .option("-c, --config <config>", "configuration file (.yaml)")
        .description("express Demo Application Server")
    
    program.parse(process.argv)
    const options = program.opts()
    const path = options.config as string
    const configData = fs.readFileSync(path, "utf-8")
    const settings = load(configData) as Settings

    return settings
}