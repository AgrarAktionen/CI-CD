// template literal handler
export function html(innerHtml: string[], ...keys: string[]) {
    const template = document.createElement("template")
    let raw = ""
    for(let i = 0; i < innerHtml.length; i++) {
        raw += innerHtml[i]
        if (keys.length > i) {
            raw += keys[i]
        }
    }
    template.innerHTML = raw
    return template
}

