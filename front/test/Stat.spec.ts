import Stat from '../app/Stat';

describe(Stat, () => {
    describe('upper()', () => {
        it('should apply lodash capitalize', () => {
            const upper = new Stat('foo', -1).upper();
            expect(typeof upper).toBe('string');
            expect(upper).toBe('Foo');
        });
    });
});
