const path = require('path');

module.exports = {
    preset: 'ts-jest',
    testEnvironment: 'jsdom',
    setupTestFrameworkScriptFile: './test-setup.js',
    moduleNameMapper: {
        '^.+\\.(css|less|scss)$': path.resolve(__dirname, '__mocks__/stylesMock.js'),
    },
};
