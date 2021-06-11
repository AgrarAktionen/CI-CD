import * as core from "express-serve-static-core"
import em from "./entity-manager"
import {School} from "./model/school"

class Router {
    async startApp(app: core.Express, port: number) {
        this.setupRoutes(app)
    }
    private setupRoutes(app: core.Express) {
        app.get("/", (_, res) => {
            res.set("Content-Type", "text/plain").send("hello world")
        })
        app.get("/api/school", async (_, res) => {
            const schools = await em.createQuery<School>("School")
            res.send(schools)
        })
    }
}
const router = new Router()
export default router
