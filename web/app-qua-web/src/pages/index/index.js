// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/index'
import store from './store'
import http from '^/component/http'
import dom from '^/component/dom'
import I18nUtilsEx from '^/component/i18n'

Vue.config.productionTip = false

I18nUtilsEx.setDefaultLang("zh");
const i18n = I18nUtilsEx.initI18n({
  'zh': require('./i18n/zh'),
  'en': require('./i18n/en')
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  http,
  store,
  dom,
  i18n,
  components: { App },
  template: '<App/>'
})
