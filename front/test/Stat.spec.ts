import Stat from '../app/Stat';

describe(Stat, () => {
    describe('upper()', () => {
        it('should apply lodash capitalize', () => {
            const upper = new Stat('foo', -1).upper();
            expect(typeof upper).toBe('string');
            expect(upper).toBe('Foo');
        });

        it('should handle multiple words stats', () => {
            expect(new Stat('foo-bar', -1).upper()).toBe('Foo bar');
        });

        it('should have a special handle for "hp"', () => {
            expect(new Stat('hp', -1).upper()).toBe('HP');
        });
    });
});
