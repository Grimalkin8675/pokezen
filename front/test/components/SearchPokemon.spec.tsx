import * as React from 'react';
import { shallow, mount } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import SearchPokemon from '../../app/components/SearchPokemon';
import { resolveFooBar, rejectSomeReason } from '../__mocks__/pokemonsGetters';


describe(SearchPokemon, () => {
    describe('render', () => {
        it('should contain an input', () => {
            const searchPokemon = shallow(
                <SearchPokemon getter={resolveFooBar} />
            );
            expect(searchPokemon.find('input').prop('type')).toBe('text');
        });

        it('should contain the pokemons\' names when promise has been resolved', () => {
            const searchPokemon = mount(
                <MemoryRouter>
                    <SearchPokemon getter={resolveFooBar} />
                </MemoryRouter>
            );
            return new Promise(r => setTimeout(r, 1000)).then(() => {
                // from resolveFooBar mock
                expect(searchPokemon.html().includes('Foo')).toBe(true);
                expect(searchPokemon.html().includes('Bar')).toBe(true);
                searchPokemon.unmount();
            });
        });

        it('should filter pokemons by name', () => {
            const searchPokemon = mount(
                <MemoryRouter>
                    <SearchPokemon getter={resolveFooBar} />
                </MemoryRouter>
            );
            searchPokemon.find(SearchPokemon).setState({ searchString: 'foo' });
            return new Promise(r => setTimeout(r, 1000)).then(() => {
                expect(searchPokemon.html().includes('Foo')).toBe(true);
                expect(searchPokemon.html().includes('Bar')).toBe(false);
                searchPokemon.unmount();
            });
        });

        it('should show message when no pokemons could be retrieved', () => {
            const searchPokemon = mount(
                <MemoryRouter>
                    <SearchPokemon getter={rejectSomeReason} />
                </MemoryRouter>
            );
            return new Promise(r => setTimeout(r, 1000))
            .then(() => {
                expect(searchPokemon.html().includes('Foo')).toBe(false);
                expect(searchPokemon.html().includes('Bar')).toBe(false);
                expect(
                    searchPokemon.html()
                        .includes('Couldn\'t retrieve pokemons.')
                ).toBe(true);
                searchPokemon.unmount();
            });
        });
    });
});
