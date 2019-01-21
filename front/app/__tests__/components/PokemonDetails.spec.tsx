import * as React from 'react';
import { mount } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import PokemonDetails from '../../components/PokemonDetails';
import Name from '../../Name';
import { resolveFoo } from '../../__mocks__/pokemonDetailsGetters';


describe(PokemonDetails, () => {
    describe('render', () => {
        const after1s = new Promise(resolve => setTimeout(resolve, 1000));

        it('should contain pokemon\'s name', () => {
            const pokemonDetails = mount(
                <MemoryRouter>
                    <PokemonDetails name={new Name('foo')}
                                    getter={resolveFoo} />
                </MemoryRouter>
            );
            return after1s.then(() => {
                expect(pokemonDetails.html().includes('Foo')).toBe(true);
                pokemonDetails.unmount();
            });
        });
    });
});
