import Vue from 'vue'
import Router from 'vue-router'
import Transform from '../components/Transform'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Transform',
      component: Transform
    }
  ]
})
