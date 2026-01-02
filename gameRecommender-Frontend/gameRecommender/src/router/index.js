// import Vue from 'vue'
// import { pa } from 'element-plus/es/locale'
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
  {
    path:'/login',
    name:'Login',
    component: () => import('../views/elements/loginPage.vue'),
  },
  {
    path:'/data',
    name:'Data',
    component: () => import('../views/elements/dataPage.vue'),
  }
]

// const router = new VueRouter({
//   mode: 'hash',
//   routes,
// })

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
})
