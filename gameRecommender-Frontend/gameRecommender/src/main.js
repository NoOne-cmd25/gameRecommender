import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import Vue from 'vue'
import router from './router'

// createApp(App).mount('#app')

const app = createApp(App)

app.use(ElementPlus)
app.use(router)
app.mount('#app')
// Vue.use(ElementPlus)

// new Vue({
//   router,
//   render: (h) => h(App),
// }).$mount('#app')
