import * as React from 'react';
import { shallow } from 'enzyme';

import App from '../components/App';


describe(App, () => {
    describe('render', () => {
        it('should render', () => {
            const app = shallow(<App />);
            expect(app.text()).toBe('It works!');
        });
    });
});
