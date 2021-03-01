
const axios = require("axios")

const base = "https://api.github.com"
const user = process.argv[2]
const token = process.argv[3]
const basicAuth = Buffer.from(`${user}:${token}`).toString("base64")

const authHeader = `Basic ${basicAuth}`
const url = `${base}/repos/caberger/javafx-cdi-jpa/releases`

async function releases() {
    const response = await axios.get(url, {
        method: "GET",
        headers: {
            Authorization: authHeader
        }
    })
    const releases = response.data
    releases.map(r => r.tag_name).forEach(n => console.log("tag", n))
    //releases.forEach(r => console.log("-->", r))
}
releases()

