import * as React from 'react';
import { shallow } from 'enzyme';

import Pokemon from '../../components/Pokemon';
import Name from '../../Name';
import { resolveFoo } from '../../__mocks__/pokemonDetailsGetters';


describe(Pokemon, () => {
    describe('render', () => {
        it('should contain pokemon\'s name', () => {
            const searchPokemon = shallow(
                <Pokemon name={new Name('foo')}
                         getter={resolveFoo} />
            );
            expect(searchPokemon.html().includes('Foo')).toBe(true);
        });
    });
});
