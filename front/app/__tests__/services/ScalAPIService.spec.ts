import ScalAPIService from '../../services/ScalAPIService';


describe(ScalAPIService, () => {
    describe('getInstance()', () => {
        it('should return a ScalAPIService instance', () => {
            const instance = ScalAPIService.getInstance();
            expect(instance).not.toBe(null);
            expect(instance).toBeInstanceOf(ScalAPIService);
            expect(instance).toBe(ScalAPIService.getInstance());
        });
    });
});
