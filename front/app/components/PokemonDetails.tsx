import * as React from 'react';

import ComparedPokemon from '../ComparedPokemon';
import Name from '../Name';


export interface IPokemonDetailsGetter {
    pokemonDetails: (name: Name) => Promise<ComparedPokemon>;
}


interface IProps {
    getter: IPokemonDetailsGetter;
    name: Name;
}

interface IState {
    pokemon: IBodyGetter;
}

export default class PokemonDetails extends React.Component<IProps, IState> {
    state: IState = {
        pokemon: new Loading(),
    };

    componentDidMount() {
        this.props.getter.pokemonDetails(this.props.name)
        .then(pokemon => this.setState({
            pokemon: new GotPokemon(pokemon)
        }))
        .catch(_message => this.setState({
            pokemon: new PokemonsError()
        }));
    }

    render() {
        return (
            <div className='Pokemon'>
                {this.state.pokemon.body(this.props.name)}
            </div>
        );
    }
}


interface IBodyGetter {
    body(name: Name): string | JSX.Element[];
}

class Loading implements IBodyGetter {
    body(): string {
        return 'Loading...';
    }
}

class GotPokemon implements IBodyGetter {
    private pokemon: ComparedPokemon;

    constructor (pokemon: ComparedPokemon) {
        this.pokemon = pokemon;
    }

    body(): string | JSX.Element[] {
        return this.pokemon.name.upper().toString();
    }
}

class PokemonsError implements IBodyGetter {
    body(name: Name): string {
        return `Couldn't retrieve pokemon ${name.upper()}`;
    }
}
