import Names from '../Names';


export interface IPokemonsGetter {
    pokemons: Promise<Names>;
}
