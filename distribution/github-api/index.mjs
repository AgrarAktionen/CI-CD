
const axios = require("axios")

const base = "https://api.github.com"
const user = process.argv[2]
const token = process.argv[3]
const basicAuth = Buffer.from(`${user}:${token}`).toString("base64")

const authHeader = `Basic ${basicAuth}`
console.log(`user=${user}, token=${token}, auth=${authHeader}`)
const url = `${base}/repos/caberger/javafx-cdi-jpa/releases`

async function releases() {
    const response = await axios.get(url, {
        method: "GET",
        headers: {
            Authorization: authHeader
        }
    })
    const releases = response.data
    releases.forEach(r => console.log("-->", r))
}
releases()

