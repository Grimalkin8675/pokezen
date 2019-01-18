import { IPokemonsGetter } from '../components/SearchPokemon';
import Name from '../Name';
import Names from '../Names';

export const resolveFooBar: IPokemonsGetter = {
    pokemons: () => new Promise(resolve =>
        resolve(new Names(
            new Name('bar'),
            new Name('foo'),
        ))
    )
};

export const rejectSomeReason: IPokemonsGetter = {
    pokemons: () => new Promise((_resolve, reject) =>
        reject('some reason')
    )
};
