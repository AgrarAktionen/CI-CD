// template literal handler
export function html(innerHtml: string, ...keys: string[]) {
    const template = document.createElement("template")
    template.innerHTML = innerHtml
    return template
}

