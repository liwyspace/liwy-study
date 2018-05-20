import _ from 'lodash';
import $ from 'jquery';
import './commons/jqplugin.js';
import Math from './commons/comutil.js';

import '../styles/commons/style.css';
import '../styles/commons/main.scss';
import '../styles/index.scss';


$(function() {
    console.log('aaaaaaaaaaaaaaaaa');
    console.log('a + b = ' + Math.add(10, 3));
    console.log('a - b = ' + Math.sub(10, 3));
    console.log(_.assign({'a': 1}, {'b': 2}, {'c': 3}));
    $('#article p:first').css({'color': '#FF0099'});
    $('#article>h1').green();
    $('#article').append('<p class="blue">蓝色</p>');
});