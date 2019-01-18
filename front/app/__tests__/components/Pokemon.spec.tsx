import * as React from 'react';
import { shallow } from 'enzyme';

import Pokemon from '../../components/Pokemon';
import Name from '../../Name';


describe(Pokemon, () => {
    describe('render', () => {
        it('should contain pokemon\'s name', () => {
            const searchPokemon = shallow(
                <Pokemon name={new Name('foo')} />
            );
            expect(searchPokemon.html().includes('Foo')).toBe(true);
        });
    });
});
