import { IPokemonDetailsGetter } from '../components/PokemonDetails';
import Name from '../Name';
import ComparedPokemon from '../ComparedPokemon';
import ImageURL from '../ImageURL';
import Type from '../Type';
import Stat from '../Stat';
import ComparedStat from '../ComparedStat';


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
