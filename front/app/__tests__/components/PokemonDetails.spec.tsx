import * as React from 'react';
import { mount, shallow } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import PokemonDetails from '../../components/PokemonDetails';
import Name from '../../Name';
import { resolveFoo } from '../__mocks__/pokemonDetailsGetters';


describe(PokemonDetails, () => {
    describe('render', () => {
        it('should contain pokemon\'s name', () => {
            const pokemonDetails = mount(
                <MemoryRouter>
                    <PokemonDetails name={new Name('foo')}
                                    getter={resolveFoo} />
                </MemoryRouter>
            );
            return new Promise(r => setTimeout(r, 1000)).then(() => {
                expect(pokemonDetails.html().includes('Foo')).toBe(true);
                pokemonDetails.unmount();
            });
        });

        it('should contain Loading', () => {
            const pokemonDetails = shallow(
                <PokemonDetails name={new Name('foo')}
                                getter={resolveFoo} />
            );
            expect(pokemonDetails.html().includes('Loading')).toBe(true);
        });

        it('should contain error message', () => {
            const pokemonDetails = mount(
                <MemoryRouter>
                    <PokemonDetails name={new Name('kaboum')}
                                    getter={resolveFoo} />
                </MemoryRouter>
            );
            return new Promise(r => setTimeout(r, 1000)).then(() => {
                expect(pokemonDetails.html().includes(
                    `Couldn't retrieve pokemon Kaboum`
                )).toBe(true);
                pokemonDetails.unmount();
            });
        });
    });
});
