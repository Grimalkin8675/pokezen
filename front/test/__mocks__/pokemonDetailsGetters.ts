import { IPokemonDetailsGetter } from '../../app/components/PokemonDetails';
import Name from '../../app/models/Name';
import ComparedPokemon from '../../app/models/ComparedPokemon';
import ImageURL from '../../app/models/ImageURL';
import Type from '../../app/models/Type';
import Stat from '../../app/models/Stat';
import ComparedStat from '../../app/models/ComparedStat';


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
