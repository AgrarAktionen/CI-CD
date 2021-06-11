import express from "express"
import router from "./app-router"
import {loadSettings} from "./settings"
import em from "./entity-manager"

const port = 8080
const app = express()
app.use(express.static("html"))

app.use((req, res, next) => {
    console.log("filter")
    if (req.url.startsWith("/api/")) {
        res.set("Content-Type", "application/json")
    }
    next()
})
async function start() {
    const settings = loadSettings()
    console.log("loaded settings", settings)
    await router.startApp(app, port)
    console.log("router start called")
    em.initialize(settings.database)

    app.listen(port, () => {
        console.log(`server is listening on port ${port}`)
    })
}

start()
