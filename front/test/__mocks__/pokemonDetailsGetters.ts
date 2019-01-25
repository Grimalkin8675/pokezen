import { IPokemonDetailsGetter } from '../../app/components/PokemonDetails';
import Name from '../../app/Name';
import ComparedPokemon from '../../app/ComparedPokemon';
import ImageURL from '../../app/ImageURL';
import Type from '../../app/Type';
import Stat from '../../app/Stat';
import ComparedStat from '../../app/ComparedStat';


export const resolveFoo: IPokemonDetailsGetter = {
    pokemonDetails: (name: Name) => new Promise((resolve, reject) => {
        if (name.toString() === 'foo') {
            resolve(
                new ComparedPokemon(
                    name,
                    new ImageURL('some url'),
                    [new Type('fire')],
                    [new Stat('def', 50)],
                    new ComparedStat(
                        'def',
                        {
                            fire: 3
                        }
                    )
                )
            );
        } else {
            reject('Unknown Pokemon');
        }
    }
    )
};
