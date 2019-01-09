import ScalAPIService from '../../services/ScalAPIService';


describe(ScalAPIService, () => {
    describe('constructor', () => {
        it('should create a ScalAPIService', () => {
            const service = new ScalAPIService();
            expect(service).toBeInstanceOf(ScalAPIService);
        });
    });
});
