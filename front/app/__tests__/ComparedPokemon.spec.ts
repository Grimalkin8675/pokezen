import ComparedPokemon from '../ComparedPokemon';
import Name from '../Name';
import ImageURL from '../ImageURL';
import Types from '../Types';
import Type from '../Type';
import Stats from '../Stats';
import Stat from '../Stat';
import ComparedStat from '../ComparedStat';


describe(ComparedPokemon, () => {
    describe('constructor', () => {
        it('should construct', () => {
            expect(
                new ComparedPokemon(
                    new Name('foo'),
                    new ImageURL('some url'),
                    new Types(new Type('fire')),
                    new Stats(new Stat('def', 50)),
                    new ComparedStat(
                        'def',
                        {
                            fire: 3
                        }
                    )
                )
            ).toBeInstanceOf(ComparedPokemon);
            expect(
                new ComparedPokemon(
                    new Name(''),
                    null,
                    new Types(),
                    new Stats()
                )
            ).toBeInstanceOf(ComparedPokemon);
        });
    });
});
