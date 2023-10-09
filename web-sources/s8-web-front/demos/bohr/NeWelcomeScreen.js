import { NeView } from "./NeView";

export class NeWelcomeScreen extends NeView {

    constructor(index) {
        super(index);
    }

    set(code, value, bindings) {
        let _layer = this;
        switch (code) {

            case 0x02: // rows
                bindings.push(function(nodes) {
                    value.forEach(function(index) {
                        _layer.node.appendChild(nodes[index]);
                    });
                });
                break;

            default: throw "This code is not supported";
        }
    }
}

