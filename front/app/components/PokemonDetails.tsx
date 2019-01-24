import * as React from 'react';

import ComparedPokemon from '../ComparedPokemon';
import Name from '../Name';
import Type from '../Type';

import * as styles from './styles/PokemonDetails.css';


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
        .then(pokemon => {
            this.setState({ pokemon: new GotPokemon(pokemon) });
        })
        .catch(_message => {
            this.setState({ pokemon: new PokemonsError() });
        });
    }

    render() {
        return (
            <div className={styles.pokemonDetails}>
                {this.state.pokemon.body(this.props.name)}
            </div>
        );
    }
}


interface IBodyGetter {
    body(name: Name): string | JSX.Element;
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

    body(): JSX.Element {
        return (
            <React.Fragment>
                <header>
                    <h1>{this.pokemon.name.upper().toString()}</h1>
                    <div className='image'>
                        {this.pokemon.image !== null
                            ? <img src={this.pokemon.image.toString()}
                                alt={this.pokemon.name.toString()} />
                            : null}
                    </div>
                </header>
                {/* <div dangerouslySetInnerHTML={{ __html: JSON.stringify(this.pokemon, undefined, 2).replace(/\n/g, '<br>').replace(/ /g, '&nbsp;') }} /> */}
                <table>
                    <thead>
                        <tr>
                            <th/>
                            {this.pokemon.types.map((type: Type, i: number) =>
                                <th key={i}>{type.toString()}</th>)}
                        </tr>
                    </thead>
                </table>
            </React.Fragment>
        );
    }
}

class PokemonsError implements IBodyGetter {
    body(name: Name): string {
        return `Couldn't retrieve pokemon ${name.upper()}`;
    }
}
