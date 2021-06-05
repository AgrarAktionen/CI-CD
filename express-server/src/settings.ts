import { Command } from 'commander'

interface DatabaseSettings {
    host: string,
    username: string,
    password: string
}

interface Settings {
    databaseSettings: DatabaseSettings
}

function loadSettings() {
    const program = new Command()
    program
        .version("0.0.1")
        .option("-c, --config <config>", "configuration file (.yaml)")
        .description("express Demo Application Server")
    
    program.parse(process.argv)
    const options = program.opts()
    
}