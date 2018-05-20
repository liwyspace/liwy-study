import Math from './commons/comutil.js';
import _ from 'lodash';
import '../styles/commons/style.css';
import '../styles/about.css';

(function() {
    console.log('bbbbbbbbbbbbbbb');
    console.log('a + b = ' + Math.add(20, 4));
    console.log('a - b = ' + Math.sub(20, 4));
    console.log(_.map([1, 2, 3], function(n) { return n * 3; }));
})();
