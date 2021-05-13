console.log("compute base href...")

let base = window.location.href
while(!base.endsWith('/')) {
    base = base.substring(0, base.length - 1)
}
window['base-href'] = base
console.log("base is", base)
