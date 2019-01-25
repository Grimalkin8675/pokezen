import * as _ from 'lodash';

export default class Stat {
    name: string;
    value: number;

    constructor (name: string, value: number) {
        this.name = name;
        this.value = value;
    }

    static fromAny(thing: any): Stat | null {
        const _thing = _(thing);
        if (  _thing.isPlainObject() && _thing.keys().size() === 2
           && _thing.has('name') && _.isString(thing.name)
           && _thing.has('value') && _.isNumber(thing.value)) {
            return new Stat(thing.name as string, thing.value as number);
        }
        return null;
    }

    upper(): string {
        if (this.name === 'hp') return 'HP';
        return _.capitalize(this.name).replace('-', ' ');
    }
}
