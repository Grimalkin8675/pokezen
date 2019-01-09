import ScalAPIService from '../../services/ScalAPIService';


describe(ScalAPIService, () => {
    describe('get instance()', () => {
        it('should return a ScalAPIService instance', () => {
            const instance = ScalAPIService.instance;
            expect(instance).not.toBe(null);
            expect(instance).toBeInstanceOf(ScalAPIService);
            expect(instance).toBe(ScalAPIService.instance);
        });
    });
});
