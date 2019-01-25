import * as React from 'react';
import { shallow } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import AppRouter from '../../app/components/AppRouter';
import NotFound from '../../app/components/NotFound';
import PokemonDetails from '../../app/components/PokemonDetails';
import SearchPokemon from '../../app/components/SearchPokemon';
import Name from '../../app/models/Name';
import { resolveFooBar } from '../__mocks__/pokemonsGetters';
import { resolveFoo } from '../__mocks__/pokemonDetailsGetters';


describe(AppRouter, () => {
    describe('route /', () => {
        const rootRouter = (child: JSX.Element) =>
            <MemoryRouter initialEntries={['/']}>{child}</MemoryRouter>;
        const root = shallow(
            <AppRouter getRouter={rootRouter}
                       pokemonsGetter={resolveFooBar}
                       pokemonDetailsGetter={resolveFoo} />
        );

        it('should contain SearchPokemon component', () => {
            const searchPokemon = shallow(
                <SearchPokemon getter={resolveFooBar}/>
            );
            const rootIncludesSearchPokemon =
                root.html().includes(searchPokemon.html());
            expect(rootIncludesSearchPokemon).toBe(true);
        });

        it('shouldn\'t contain NotFound component', () => {
            const notFound = shallow(
                <MemoryRouter><NotFound/></MemoryRouter>);
            const rootIncludesNotFound =
                root.html().includes(notFound.html());
            expect(rootIncludesNotFound).toBe(false);
        });
    });

    describe('route /kaboum', () => {
        const kaboumRouter = (child: JSX.Element) =>
            <MemoryRouter initialEntries={['/kaboum']}>{child}</MemoryRouter>;
        const kaboum = shallow(
            <AppRouter getRouter={kaboumRouter}
                       pokemonsGetter={resolveFooBar}
                       pokemonDetailsGetter={resolveFoo} />
        );

        it('should contain NotFound component', () => {
            const notFound = shallow(
                <MemoryRouter><NotFound/></MemoryRouter>);
            const kaboumIncludesNotFound =
                kaboum.html().includes(notFound.html());
            expect(kaboumIncludesNotFound).toBe(true);
        });

        it('should\'t contain SearchPokemon component', () => {
            const searchPokemon = shallow(
                <SearchPokemon getter={resolveFooBar} />
            );
            const rootIncludesSearchPokemon =
                kaboum.html().includes(searchPokemon.html());
            expect(rootIncludesSearchPokemon).toBe(false);
        });
    });

    describe('route /pokemon/:name', () => {
        const pokemonRouter = (child: JSX.Element) =>
            <MemoryRouter initialEntries={['/pokemon/foo']}>
                {child}
            </MemoryRouter>;
        const app = shallow(
            <AppRouter getRouter={pokemonRouter}
                       pokemonsGetter={resolveFooBar}
                       pokemonDetailsGetter={resolveFoo} />
        );

        it('should contain Pokemon component', () => {
            const pokemon = shallow(
                <PokemonDetails name={new Name('foo')}
                                getter={resolveFoo} />
            );
            expect(app.html().includes(pokemon.html())).toBe(true);
        });
    });
});
