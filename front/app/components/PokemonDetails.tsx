import * as React from 'react';

import ComparedPokemon from '../models/ComparedPokemon';
import Stat from '../models/Stat';
import Name from '../models/Name';
import Type from '../models/Type';

import { hoverComparedStat } from '../utils';

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
            <div>
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
        const nameImage = (
            <div className={styles.nameImage}>
                <h1>{this.pokemon.name.upper()}</h1>
                <div className={styles.image}>
                    {this.pokemon.image !== null
                        ? <img src={this.pokemon.image.toString()}
                               alt={this.pokemon.name.upper()} />
                        : <div className={styles.emptyImage}>?</div>}
                </div>
            </div>);

        const thead = (
            <thead>
                <tr>
                    <th colSpan={2}
                        className={styles.datasTitle}>
                        Type{this.pokemon.types.length > 1 ? 's' : ''}
                    </th>
                    {this.pokemon.types.length !== 0
                        ? this.pokemon.types.map((type, i) =>
                              <th key={i}>{type.upper()}</th>)
                        : 'unknown'}
                </tr>
                <tr>
                    <th className={styles.datasTitle}>Stats</th>
                    <th className={styles.baseStatsTh}>base</th>
                </tr>
            </thead>);

        const stats =
            this.pokemon.baseStats
                .sort((a, b) => a.name > b.name ? 1 : -1)
                .map(this.stat);

        return (
            <React.Fragment>
                {nameImage}
                <table className={styles.stats}>
                    {thead}
                    <tbody>
                        {stats}
                    </tbody>
                </table>
            </React.Fragment>
        );
    }

    private stat = (stat: Stat, i: number): JSX.Element => {
        const comparedStat = (type: Type, j: number): JSX.Element => {
            const value = this.pokemon.comparedStatToType(stat, type);
            if (value === undefined) return <div key={j} />;
            const rounded = Math.round(value);
            const color: string | undefined = (() => {
                if (rounded < 0) return styles.red;
                if (rounded > 0) return styles.green;
                return undefined;
            })();
            return (
                <td key={j}
                    className={styles.comparedStat+' '+color}
                    title={hoverComparedStat(
                        this.pokemon.name,
                        new Stat(stat.name, rounded),
                        type
                    )}>
                    {rounded}
                </td>);
        };

        return (
            <tr key={i}>
                <td>{stat.upper()}</td>
                <td className={styles.baseStat}>{stat.value}</td>
                {this.pokemon.types.map(comparedStat)}
            </tr>);
    }
}

class PokemonsError implements IBodyGetter {
    body(name: Name): string {
        return `Couldn't retrieve pokemon ${name.upper()}`;
    }
}
