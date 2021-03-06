import Name from './models/Name';
import Stat from './models/Stat';
import Type from './models/Type';


export const hoverComparedStat = (
    name: Name,
    stat: Stat,
    type: Type
): string => {
    if (stat.value === 0) return `${name.upper()} has as much base ${stat.name} as the average ${type} pokemon.`;

    const absValue = Math.abs(stat.value);
    const moreOrLess = stat.value > 0 ? 'more' : 'less';
    return `${name.upper()} has ${absValue} ${moreOrLess} base ${stat.name} compared to the average ${type} pokemon.`;
};
