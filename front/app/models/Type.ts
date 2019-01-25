import { capitalize } from 'lodash';


export default class Type {
    private type: string;

    constructor (type: string) {
        this.type = type;
    }

    toString(): string {
        return this.type;
    }

    upper(): string {
        return capitalize(this.type);
    }
}
