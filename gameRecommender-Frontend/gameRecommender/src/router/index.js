import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'MainPage',
    component: () => import('../views/elements/mainPage.vue'),
  },
  {
    path: '/test',
    name: 'Test',
    component: () => import('../views/elements/test.vue'),
  },
]

const router = new VueRouter({
  mode: 'hash',
  routes,
})

export default router
