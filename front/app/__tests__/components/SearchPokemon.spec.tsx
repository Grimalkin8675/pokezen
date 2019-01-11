import * as React from 'react';
import { shallow, mount } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import SearchPokemon from '../../components/SearchPokemon';
import pokemonsGetter from '../../__mocks__/pokemonsGetter';


describe(SearchPokemon, () => {
    describe('render', () => {
        it('should contain an input', () => {
            const searchPokemon = shallow(
                <SearchPokemon getter={pokemonsGetter} />
            );
            expect(searchPokemon.find('input').prop('type')).toBe('text');
        });

        it('should contain the pokemons\' names when promise has been resolved', () => {
            const searchPokemon = mount(
                <MemoryRouter>
                    <SearchPokemon getter={pokemonsGetter} />
                </MemoryRouter>
            );
            const after1s = new Promise(resolve => setTimeout(resolve, 1000));
            return after1s.then(() => {
                // from pokemonsGetter mock
                expect(searchPokemon.html().includes('Foo')).toBe(true);
                expect(searchPokemon.html().includes('Bar')).toBe(true);
                searchPokemon.unmount();
            });
        });

        it('should filter pokemons by name', () => {
            const searchPokemon = mount(
                <MemoryRouter>
                    <SearchPokemon getter={pokemonsGetter} />
                </MemoryRouter>
            );

            searchPokemon.find(SearchPokemon).setState({ searchString: 'foo' });

            const after1s = new Promise(resolve => setTimeout(resolve, 1000));
            return after1s.then(() => {
                expect(searchPokemon.html().includes('Foo')).toBe(true);
                expect(searchPokemon.html().includes('Bar')).toBe(false);
                searchPokemon.unmount();
            });
        });
    });
});
