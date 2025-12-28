// import Vue from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'

// Vue.use(VueRouter)

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

// const router = new VueRouter({
//   mode: 'hash',
//   routes,
// })

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
})
