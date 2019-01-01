export default class Name {
    private name: string;

    constructor (name: string) {
        this.name = name;
    }

    toString(): string {
        return this.name;
    }
}
