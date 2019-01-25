export default class ImageURL {
    private url: string;

    constructor (url: string) {
        this.url = url;
    }

    toString(): string {
        return this.url;
    }
}
