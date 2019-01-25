import * as React from 'react';
import { Link } from 'react-router-dom';

import Names from '../models/Names';

import * as styles from './styles/SearchPokemon.css';


export interface IPokemonsGetter {
    pokemons: () => Promise<Names>;
}


interface IProps {
    getter: IPokemonsGetter;
}

interface IState {
    names: IBodyGetter;
    searchString: string;
}

export default class SearchPokemon extends React.Component<IProps, IState> {
    private inputRef: React.RefObject<HTMLInputElement> = React.createRef();

    state: IState = {
        names: new Loading(),
        searchString: '',
    };

    componentDidMount() {
        this.props.getter.pokemons()
        .then(pokemonNames => {
            this.setState({ names: new PokemonLinks(pokemonNames) });
        })
        .catch(_message => {
            this.setState({ names: new PokemonsError() });
        });
    }

    render() {
        return (
            <div className={styles.main}>
                <h1 className={styles.title}>Pokezen</h1>
                <div className={styles.splitVertically}>
                    <div className={styles.searchBar}>
                        <input ref={this.inputRef}
                            onChange={this.onChange}
                            type='text'
                            placeholder='Search for pokemons!' />
                    </div>
                    <div className={styles.pokemons}>
                        {this.state.names.body(this.state.searchString)}
                    </div>
                </div>
            </div>
        );
    }

    private onChange = (e: React.FormEvent) => {
        if (this.inputRef.current !== null) {
            this.setState({ searchString: this.inputRef.current.value });
        }
    }
}


interface IBodyGetter {
    body(searchString: string): JSX.Element;
}

class Loading implements IBodyGetter {
    body(): JSX.Element {
        return (
            <div className={styles.almostALink}>
                Loading...
            </div>
        );
    }
}

class PokemonLinks implements IBodyGetter {
    private names: Names;

    constructor (names: Names) {
        this.names = names;
    }

    body(searchString: string): JSX.Element {
        if (this.names.isEmpty()) {
            return (
                <div className={styles.almostALink}>
                    No Pokemons :(
                </div>
            );
        }

        const filtered = this.names.filterByString(searchString.toLowerCase());
        if (filtered.isEmpty()) {
            return (
                <div className={styles.almostALink}>
                    No matching Pokemons.
                </div>
            );
        }

        const links = filtered.map((name, i) =>
            <Link key={i} to={`/pokemon/${name}`} className={styles.link}>
                {name.upper()}
            </Link>);

        return <div>{links}</div>;
    }
}

class PokemonsError implements IBodyGetter {
    body(): JSX.Element {
        return (
            <div className={styles.almostALink}>
                Couldn't retrieve pokemons.
            </div>
        );
    }
}
