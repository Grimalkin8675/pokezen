export interface IWSClient {
    get: (url: string) => Promise<any>;
}

export default class ScalAPIService {
    constructor(wsClient: IWSClient) {
        //
    }
}
