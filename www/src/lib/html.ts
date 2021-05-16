// template literal handler

export function html(innerHtml: string[], ...keys: string[]) {
    const template = document.createElement("template")
    let s = ""
    for(let i = 0; i < innerHtml.length; i++) {
        s += innerHtml[i]
        if (keys.length > i) {
            s += keys[i]
        }
    }
    template.innerHTML = s
    return template
}

