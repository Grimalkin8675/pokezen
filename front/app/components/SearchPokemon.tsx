import * as React from 'react';
import { Link } from 'react-router-dom';

import { IPokemonsGetter } from '../services/ScalAPIService';
import Names from '../Names';
import Name from '../Name';


interface IProps {
    getter: IPokemonsGetter;
}

interface IState {
    names: Names | null;
}

export default class SearchPokemon extends React.Component<IProps, IState> {
    state: IState = {
        names: null,
    };

    constructor (props: IProps) {
        super(props);
        this.props.getter.pokemons
            .then(pokemonNames => this.setState({ names: pokemonNames }));
    }

    render() {
        return (
            <div className='SearchPokemon'>
                <input type='text' placeholder='Search for pokemons!'/>
                {this.names()}
            </div>
        );
    }

    private names = (): JSX.Element => {
        const body = (): string | JSX.Element[] => {
            if (this.state.names === null) return 'Loading...';
            if (this.state.names.isEmpty()) return 'No Pokemons :(';
            return this.state.names.map((name: Name, i: number) => (
                <Link key={i} to={`/pokemon/${name}`}>
                    {name.upper().toString()}
                </Link>
            ));
        };
        return <div>{body()}</div>;
    }
}
