import Type from './Type';


export default class Types {
    types: Type[];

    constructor (...types: Type[]) {
        this.types = types;
    }
}
