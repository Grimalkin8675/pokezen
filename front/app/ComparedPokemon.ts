import * as _ from 'lodash';

import Name from './Name';
import ImageURL from './ImageURL';
import Type from './Type';
import Stat from './Stat';
import ComparedStat from './ComparedStat';


export default class ComparedPokemon {
    name: Name;
    image: ImageURL | null;
    types: Type[];
    baseStats: Stat[];
    comparedStats: ComparedStat[];

    constructor (
        name: Name,
        image: ImageURL | null,
        types: Type[],
        baseStats: Stat[],
        ...comparedStats: ComparedStat[]
    ) {
        this.name = name;
        this.image = image;
        this.types = types;
        this.baseStats = baseStats;
        this.comparedStats = comparedStats;
    }

    static fromAny(thing: any): ComparedPokemon | null {
        const _thing = _(thing);
        if (  _thing.isPlainObject() && _thing.keys().size() === 5
           && _thing.has('name') && _.isString(thing.name)
           && _thing.has('image') && (  _.isString(thing.image)
                                     || thing.image === null)
           && _thing.has('types') && _.isArray(thing.types)
                                  && thing.types.every(_.isString)
           && _thing.has('base_stats') && _.isArray(thing.base_stats)
           && _thing.has('compared_stats') && _.isArray(thing.compared_stats))
        {
            const types: Type[] =
                (thing.types as string[])
                .map(type => new Type(type));
            const baseStats: Stat[] =
                (thing.base_stats as any[])
                .map(Stat.fromAny)
                .filter(_.negate(_.isNull)) as Stat[];
            const comparedStats: ComparedStat[] =
                (thing.compared_stats as any[])
                .map(ComparedStat.fromAny)
                .filter(_.negate(_.isNull)) as ComparedStat[];
            return new ComparedPokemon(
                new Name(thing.name as string),
                thing.image === null ? null
                                     : new ImageURL(thing.image as string),
                types,
                baseStats,
                ...comparedStats
            );
        }
        return null;
    }
}
