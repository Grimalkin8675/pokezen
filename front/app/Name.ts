import { upperFirst } from 'lodash';


export default class Name {
    private name: string;

    constructor (name: string) {
        this.name = name;
    }

    toString(): string {
        return this.name;
    }

    upper(): Name {
        return new Name(upperFirst(this.name));
    }

    matches(str: string): boolean {
        return true;
    }
}
