import Name from '../app/Name';


describe(Name, () => {
    describe('toString()', () => {
        it('should wrap the name', () => {
            expect(new Name('foo').toString()).toBe('foo');
        });
    });

    describe('upper()', () => {
        it('should apply lodash upperFirst', () => {
            const upper = new Name('foo').upper();
            expect(typeof upper).toBe('string');
            expect(upper).toBe('Foo');
        });

        it('should handle multiple words names', () => {
            expect(new Name('foo-bar').upper()).toBe('Foo Bar');
            expect(new Name('foo bar').upper()).toBe('Foo Bar');
        });
    });

    describe('includes(str)', () => {
        it('should return true for the string wrapped in Name', () => {
            expect(new Name('foo').includes('foo')).toBe(true);
        });

        it('should return false for a not matching string', () => {
            expect(new Name('foo').includes('bar')).toBe(false);
        });

        it('should return true for a matching string', () => {
            expect(new Name('bar').includes('bar')).toBe(true);
        });

        it('should return false for a not matching string 2', () => {
            expect(new Name('bar').includes('foo')).toBe(false);
        });

        it('should return true for a partially matching string', () => {
            expect(new Name('foo').includes('f')).toBe(true);
        });

        it('should return false for a not matching string 3', () => {
            expect(new Name('bar').includes('f')).toBe(false);
        });

        it('should return false for a matching string 2', () => {
            expect(new Name('foo').includes('')).toBe(true);
        });
    });
});
