import * as React from 'react';
import { shallow } from 'enzyme';

import PokemonDetails from '../../components/PokemonDetails';
import Name from '../../Name';
import { resolveFoo } from '../../__mocks__/pokemonDetailsGetters';


describe(PokemonDetails, () => {
    describe('render', () => {
        it('should contain pokemon\'s name', () => {
            const searchPokemon = shallow(
                <PokemonDetails name={new Name('foo')}
                                getter={resolveFoo} />
            );
            expect(searchPokemon.html().includes('Foo')).toBe(true);
        });
    });
});
