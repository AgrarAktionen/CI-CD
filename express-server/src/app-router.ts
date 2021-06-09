import * as core from "express-serve-static-core"

class Router {
    async startApp(app: core.Express, port: number) {
        this.setupRoutes(app)
    }
    private setupRoutes(app: core.Express) {
        app.get("/", (_, res) => {
            res.send("hello world!")
        })

    }
}
const router = new Router()
export default router
