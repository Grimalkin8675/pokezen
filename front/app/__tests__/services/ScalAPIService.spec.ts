import ScalAPIService, { IWSClient } from '../../services/ScalAPIService';
import Name from '../../Name';
import Names from '../../Names';


describe(ScalAPIService, () => {
    const getter: IWSClient = {
        get: () => new Promise(resolve =>
            resolve(new Names(new Name('foo')))),
    };

    describe('constructor', () => {
        it('should create a ScalAPIService', () => {
            const service = new ScalAPIService(getter);
            expect(service).toBeInstanceOf(ScalAPIService);
        });
    });
});
