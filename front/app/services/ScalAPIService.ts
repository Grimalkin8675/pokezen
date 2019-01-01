import Name from '../Name';


export interface IPokemonsGetter {
    getPokemons: () => Promise<Name[]>;
}
