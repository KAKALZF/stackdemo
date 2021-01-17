import Vue from 'vue'
import Router from 'vue-router'
import myComponent from './my-component'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/myComponent1',
            name: 'myComponent1',
            component: myComponent
        },
        {
            path: '/myComponent2',
            name: 'myComponent2',
            component: myComponent
        }
    ]
})
