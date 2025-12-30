import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { router } from './router/index.js'
import axios from 'axios'

// createApp(App).mount('#app')
axios.defaults.baseURL = 'http://localhost:8080'

const app = createApp(App)

app.use(ElementPlus)
app.use(router)
app.mount('#app')
