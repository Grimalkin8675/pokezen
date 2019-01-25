import { capitalize } from 'lodash';


export default class Name {
    private name: string;

    constructor (name: string) {
        this.name = name;
    }

    toString(): string {
        return this.name;
    }

    upper(): string {
        return this.name
            .split(/[ -]/)
            .map(capitalize)
            .join(' ');
    }

    includes(searchString: string): boolean {
        return this.name.includes(searchString);
    }
}
