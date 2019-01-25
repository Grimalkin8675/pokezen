import { IPokemonsGetter } from '../../app/components/SearchPokemon';
import Name from '../../app/models/Name';
import Names from '../../app/models/Names';

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
