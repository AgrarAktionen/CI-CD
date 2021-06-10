import express from "express"
import router from "./app-router"
import {loadSettings} from "./settings"
import em from "./entity-manager"
import {School} from "./model/school"

const port = 8080
const app = express()
app.use(express.static("html"))

async function start() {
    const settings = loadSettings()
    console.log("loaded settings", settings)
    await router.startApp(app, port)
    console.log("router start called")
    em.initialize(settings.database)

    const schools = await em.createQuery<School>("School") as School[]
    schools.forEach(s => console.log(s))

    app.listen(port, () => {
        console.log(`server is listening on port ${port}`)
    })
}

start()
