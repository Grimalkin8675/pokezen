import Name from '../Name';


describe(Name, () => {
    describe('toString()', () => {
        it('should wrap the name', () => {
            expect(new Name('foo').toString()).toBe('foo');
        });
    });
});
