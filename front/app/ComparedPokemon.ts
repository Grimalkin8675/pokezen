import Name from './Name';
import ImageURL from './ImageURL';
import Types from './Types';
import Stats from './Stats';
import ComparedStat from './ComparedStat';

export default class ComparedPokemon {
    name: Name;
    image: ImageURL | null;
    types: Types;
    baseStats: Stats;
    comparedStats: ComparedStat[];

    constructor (
        name: Name,
        image: ImageURL | null,
        types: Types,
        baseStats: Stats,
        ...comparedStats: ComparedStat[]
    ) {
        this.name = name;
        this.image = image;
        this.types = types;
        this.baseStats = baseStats;
        this.comparedStats = comparedStats;
    }
}
