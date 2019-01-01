import Name from '../Name';


describe(Name, () => {
    describe('constructor', () => {
        it('should wrap the name', () => {
            expect(new Name('foo').name).toBe('foo');
        });
    });
});
