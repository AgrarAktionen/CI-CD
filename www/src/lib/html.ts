/** template literal handler 
(c) Christian Aberger (http://www.aberger.at)
*/
export function html(innerHtml: string[], ...keys: string[]) {
    const template = document.createElement("template")
    let raw = ""
    innerHtml.forEach((s, i) => {
        raw += s
        if (keys.length > i) {
            raw += keys[i]
        }
    })
    template.innerHTML = raw
    return template
}

