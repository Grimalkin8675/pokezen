import Name from '../Name';


describe(Name, () => {
    describe('toString()', () => {
        it('should wrap the name', () => {
            expect(new Name('foo').toString()).toBe('foo');
        });
    });

    describe('upper()', () => {
        it('should apply lodash upperFirst', () => {
            const upper = new Name('foo').upper();
            expect(upper).toBeInstanceOf(Name);
            expect(upper.toString()).toBe('Foo');
        });
    });

    describe('matches(str)', () => {
        it('should return true for the string wrapped in Name', () => {
            expect(new Name('foo').matches('foo')).toBe(true);
        });
    });
});
