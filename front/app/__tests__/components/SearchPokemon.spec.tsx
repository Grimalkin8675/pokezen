import * as React from 'react';
import { shallow } from 'enzyme';

import SearchPokemon from '../../components/SearchPokemon';


describe(SearchPokemon, () => {
    describe('render', () => {
        it('should render', () => {
            const searchPokemon = shallow(<SearchPokemon />);
            expect(searchPokemon.contains(
                <input type='text' placeholder='Search for pokemons!' />)
            ).toBe(true);
        });
    });
});
