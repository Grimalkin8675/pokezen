import * as React from 'react';
import { Link } from 'react-router-dom';

import Names from '../Names';
import Name from '../Name';


export interface IPokemonsGetter {
    pokemons: Promise<Names | null>;
}


interface IProps {
    getter: IPokemonsGetter;
}

interface IState {
    names: Names | null;
    searchString: string;
}

export default class SearchPokemon extends React.Component<IProps, IState> {
    private inputRef: React.RefObject<HTMLInputElement> = React.createRef();

    state: IState = {
        names: null,
        searchString: '',
    };

    componentDidMount() {
        this.props.getter.pokemons
        .then(pokemonNames => this.setState({ names: pokemonNames }));
    }

    render() {
        return (
            <div className='SearchPokemon'>
                <input ref={this.inputRef}
                       onChange={this.onChange}
                       type='text'
                       placeholder='Search for pokemons!' />
                {this.links()}
            </div>
        );
    }

    private onChange = (e: React.FormEvent) => {
        if (this.inputRef.current !== null) {
            this.setState({ searchString: this.inputRef.current.value });
        }
    }

    private links = (): JSX.Element => {
        const body = (): string | JSX.Element[] => {
            if (this.state.names === null) return 'Loading...';
            if (this.state.names.isEmpty()) return 'No Pokemons :(';

            const filtered =
                this.state.names.filter(this.state.searchString.toLowerCase());
            if (filtered.isEmpty()) return 'No matching Pokemons.';
            return filtered.map((name: Name, i: number) => (
                <div key={i}>
                    <Link to={`/pokemon/${name}`}>
                        {name.upper().toString()}
                    </Link>
                </div>
            ));
        };
        return <div>{body()}</div>;
    }
}
