import { IPokemonsGetter } from '../components/SearchPokemon';
import Name from '../Name';
import Names from '../Names';

export const resolveFooBar: IPokemonsGetter = {
    pokemons: new Promise(resolve =>
        resolve(new Names(
            new Name('bar'),
            new Name('foo'),
        ))
    )
};
