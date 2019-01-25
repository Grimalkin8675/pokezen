import * as utils from '../app/utils';
import Name from '../app/models/Name';
import Type from '../app/models/Type';
import Stat from '../app/models/Stat';


describe('utils', () => {
    describe(utils.hoverComparedStat, () => {
        it('should return that a pokemon has more stat than average', () => {
            expect(
                utils.hoverComparedStat(
                    new Name('pikachu'),
                    new Stat('speed', 3),
                    new Type('electric')
                )
            ).toBe('Pikachu has 3 more base speed compared to the average electric pokemon.');
        });
        it('should return that a pokemon has less stat than average', () => {
            expect(
                utils.hoverComparedStat(
                    new Name('foo bar'),
                    new Stat('attack', -2),
                    new Type('water')
                )
            ).toBe('Foo Bar has 2 less base attack compared to the average water pokemon.');
        });
        it('should return that a pokemon has as much as average', () => {
            expect(
                utils.hoverComparedStat(
                    new Name('BAZ'),
                    new Stat('defense', 0),
                    new Type('poison')
                )
            ).toBe('Baz has as much base defense as the average poison pokemon.');
        });
    });
});
