import { upperFirst } from 'lodash';


export default class Name {
    private name: string;

    constructor (name: string) {
        this.name = name;
    }

    toString(): string {
        return this.name;
    }

    upper(): string {
        return upperFirst(this.name);
    }

    includes(searchString: string): boolean {
        return this.name.includes(searchString);
    }
}
