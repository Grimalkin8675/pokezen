import Name from './Name';
import Stat from './Stat';
import Type from './Type';


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
