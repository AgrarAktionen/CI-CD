
import express from "express"
import router from "./app-router"
import {loadSettings} from "./settings"

const port = 8080
const app = express()
app.use(express.static("html"))

async function start() {
    const settings = loadSettings()
    console.log("loaded settings", settings)
    await router.startApp(app, port)
    console.log("router start called")
    app.listen(port, () => {
        console.log(`server is listening on port ${port}`)
    })
}

start()
