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

export default class PokemonDetails extends React.Component<IProps> {
    render() {
        return (
            <div className='Pokemon'>
                {this.props.name.upper().toString()}
            </div>
        );
    }
}
