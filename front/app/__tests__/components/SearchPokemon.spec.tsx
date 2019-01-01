import * as React from 'react';
import { shallow } from 'enzyme';

import SearchPokemon from '../../components/SearchPokemon';
import pokemonsGetter from '../../__mocks__/pokemonsGetter';


describe(SearchPokemon, () => {
    describe('render', () => {
        it('should contain an input', () => {
            const searchPokemon = shallow(
                <SearchPokemon getter={pokemonsGetter}/>
            );
            expect(searchPokemon.find('input').prop('type')).toBe('text');
        });
    });
});
