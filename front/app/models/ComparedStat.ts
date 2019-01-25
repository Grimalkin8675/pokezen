import * as _ from 'lodash';


export default class ComparedStat {
    statName: string;
    comparisons: { [typeName: string]: number };

    constructor (
        statName: string,
        comparisons: { [typeName: string]: number }
    ) {
        this.statName = statName;
        this.comparisons = comparisons;
    }

    static fromAny(thing: any): ComparedStat | null {
        const _thing = _(thing);
        if (  _thing.isPlainObject() && _thing.keys().size() === 2
           && _thing.has('name') && _.isString(thing.name)
           && _thing.has('comparisons')
               && _.isPlainObject(thing.comparisons)
               && _.every(thing.comparisons, _.isNumber)) {
            return new ComparedStat(
                thing.name as string,
                thing.comparisons as { [typeName: string]: number }
            );
        }
        return null;
    }
}
