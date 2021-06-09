import express from "express"
import router from "./app-router"

const port = 8080
const app = express()
app.use(express.static("html"))

async function start() {
    await router.startApp(app, port)
    console.log("router start called")
    app.listen(port, () => {
        console.log(`server is listening on port ${port}`)
    })
}

start()
