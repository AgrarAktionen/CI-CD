# Browser REST client _using the platform only_

This project implements a pure Typescript REST - Client implemented without any
foreign UI-libraries (no Angular, no Vuejs, no React, no Redux, no lit-html, no Polymer...).
The example shows how to implement the [Model View Controller Architecture](https://aberger.at/blog/architecture/javafx/2019/10/26/mvc-pattern-javafx.html) in a browser client application.

Although we do not use Redux, this example also demonstrates the most important principle in SPA-Application Design: Immutable state. For that we use a small library: [immer](https://immerjs.github.io/immer/).

It makes use of [Custom Elements](https://developer.mozilla.org/en-US/docs/Web/Web_Components/Using_custom_elements) and the [Shadow DOM](https://developer.mozilla.org/en-US/docs/Web/Web_Components/Using_shadow_DOM). Both are implemented in native code in all popular browsers that we are interested in, so this gives optimal performance.
Other browsers are shimmed by using webcomponents.

We do mix 2 simple things: mutation and asynchronicity that - when mixed together - 
can behave like [coke and menthos](https://www.youtube.com/watch?v=ZwyMcV9emmc).
To avoid that we use [Observables](http://reactivex.io/)
and make use of 3 fundamental design principles:
- Single source of truth
- Read Only State
- Changes are made with pure functions

We use Typescript to implement this, but without the heavy overhead that would be neccessary with libraries like redux or immutablejs.

See [model.ts](./src/model/model.ts) and [store.ts](./src/model/store.ts) in the project as an example.

We do not use lit-html. Still you should install the lit-html Plugin to get the syntax coloring for the html templates in your code editor.

