// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.common with an alias.
import Vue from 'vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import App from './app.vue';
import Vue2Filters from 'vue2-filters';
import router from './router';
import * as config from './shared/config/config';
import * as bootstrapVueConfig from './shared/config/config-bootstrap-vue';
import JhiItemCountComponent from './shared/jhi-item-count.vue';
import JhiSortIndicatorComponent from './shared/sort/jhi-sort-indicator.vue';
import '../content/css/main.css';
import InfiniteLoading from 'vue-infinite-loading';
import AuditsService from './admin/audits/audits.service';

import HealthService from './admin/health/health.service';
import MetricsService from './admin/metrics/metrics.service';
import LogsService from './admin/logs/logs.service';
import ActivateService from './account/activate/activate.service';
import RegisterService from './account/register/register.service';
import UserManagementService from '@/admin/user-management/user-management.service';

import LoginService from './account/login.service';
import AccountService from './account/account.service';

import '../content/scss/vendor.scss';
import AlertService from '@/shared/alert/alert.service';
import TranslationService from '@/locale/translation.service';
import ConfigurationService from '@/admin/configuration/configuration.service';

/* tslint:disable */

import EmployeeInformationService from '@/entities/employee-information/employee-information.service';
import EmployeeSalaryService from '@/entities/employee-salary/employee-salary.service';
import EmployeeService from '@/entities/employee/employee.service';
import PayrollGenerateService from '@/entities/payroll-generate/payroll-generate.service';
import AttendanceService from '@/entities/attendance/attendance.service';
import CompanyService from '@/entities/company/company.service';
import GroupsService from '@/entities/groups/groups.service';
import LedgerService from '@/entities/ledger/ledger.service';
import GroupService from '@/entities/group/group.service';
import VoucherTypeService from '@/entities/voucher-type/voucher-type.service';
import ProductGroupsService from '@/entities/product-groups/product-groups.service';
import ProductItemService from '@/entities/product-item/product-item.service';
import UnitOfMeasureService from '@/entities/unit-of-measure/unit-of-measure.service';
import AccountingVoucherService from '@/entities/accounting-voucher/accounting-voucher.service';
import SalesVoucherTypeService from '@/entities/sales-voucher-type/sales-voucher-type.service';
import SalesVoucherTypeTotalService from '@/entities/sales-voucher-type-total/sales-voucher-type-total.service';
// jhipster-needle-add-entity-service-to-main-import - JHipster will import entities services here

/* tslint:enable */
Vue.config.productionTip = false;
config.initVueApp(Vue);
config.initFortAwesome(Vue);
bootstrapVueConfig.initBootstrapVue(Vue);
Vue.use(Vue2Filters);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.component('jhi-item-count', JhiItemCountComponent);
Vue.component('jhi-sort-indicator', JhiSortIndicatorComponent);
Vue.component('infinite-loading', InfiniteLoading);

const i18n = config.initI18N(Vue);
const store = config.initVueXStore(Vue);

const alertService = new AlertService(store);
const translationService = new TranslationService(store, i18n);
const loginService = new LoginService();
const accountService = new AccountService(store, translationService, router);

router.beforeEach((to, from, next) => {
  if (!to.matched.length) {
    next('/not-found');
  }

  if (to.meta && to.meta.authorities && to.meta.authorities.length > 0) {
    accountService.hasAnyAuthorityAndCheckAuth(to.meta.authorities).then(value => {
      if (!value) {
        sessionStorage.setItem('requested-url', to.fullPath);
        next('/forbidden');
      } else {
        next();
      }
    });
  } else {
    // no authorities, so just proceed
    next();
  }
});

/* tslint:disable */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>',
  router,
  provide: {
    loginService: () => loginService,
    activateService: () => new ActivateService(),
    registerService: () => new RegisterService(),
    userService: () => new UserManagementService(),

    auditsService: () => new AuditsService(),

    healthService: () => new HealthService(),

    configurationService: () => new ConfigurationService(),
    logsService: () => new LogsService(),
    metricsService: () => new MetricsService(),
    alertService: () => alertService,
    translationService: () => translationService,
    employeeInformationService: () => new EmployeeInformationService(),
    employeeSalaryService: () => new EmployeeSalaryService(),
    employeeService: () => new EmployeeService(),
    payrollGenerateService: () => new PayrollGenerateService(),
    attendanceService: () => new AttendanceService(),
    companyService: () => new CompanyService(),
    groupsService: () => new GroupsService(),
    ledgerService: () => new LedgerService(),
    groupService: () => new GroupService(),
    voucherTypeService: () => new VoucherTypeService(),
    productGroupsService: () => new ProductGroupsService(),
    productItemService: () => new ProductItemService(),
    unitOfMeasureService: () => new UnitOfMeasureService(),
    accountingVoucherService: () => new AccountingVoucherService(),
    salesVoucherTypeService: () => new SalesVoucherTypeService(),
    salesVoucherTypeTotalService: () => new SalesVoucherTypeTotalService(),
    // jhipster-needle-add-entity-service-to-main - JHipster will import entities services here
    accountService: () => accountService,
  },
  i18n,
  store,
});
