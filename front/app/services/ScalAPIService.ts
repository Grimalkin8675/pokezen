export default class ScalAPIService {
    private static _instance: ScalAPIService | null = null;

    private constructor() {
    }

    static getInstance(): ScalAPIService {
        if (ScalAPIService._instance === null) {
            ScalAPIService._instance = new ScalAPIService();
        }
        return ScalAPIService._instance;
    }
}
