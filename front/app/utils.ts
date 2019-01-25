import Name from './Name';
import Stat from './Stat';
import Type from './Type';


export const hoverComparedStat = (
    name: Name,
    stat: Stat,
    type: Type
): string => {
    return `${name.upper()} has ${stat.value} more base ${stat.name} compared to the average electric pokemon.`;
};
