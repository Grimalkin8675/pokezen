import * as _ from 'lodash';

import Name from './Name';


export default class Names {
    private names: Name[];

    constructor (...names: Name[]) {
        this.names = names;
    }

    static fromAny(thing: any): Names | null {
        if (  _.isArray(thing) && thing.every(_.isString) {
            const names: Name[] =
                thing.map((elt: string) => new Name(elt));
            return new Names(...names);
        }
        return null;
    }

    isEmpty(): boolean {
        return this.names.length === 0;
    }

    map<T>(f: (name: Name, i: number) => T): T[] {
        return this.names.map(f);
    }

    filter(searchString: string): Names {
        const trimed = searchString.trim();
        const filtered = this.names.filter(name => name.includes(trimed));
        return new Names(...filtered);
    }
}
