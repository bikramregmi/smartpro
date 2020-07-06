import Vue from 'vue';
import Component from 'vue-class-component';
Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate', // for vue-router 2.2+
]);
import Router from 'vue-router';
import { Authority } from '@/shared/security/authority';
const Home = () => import('../core/home/home.vue');
const Error = () => import('../core/error/error.vue');
const Register = () => import('../account/register/register.vue');
const Activate = () => import('../account/activate/activate.vue');
const ResetPasswordInit = () => import('../account/reset-password/init/reset-password-init.vue');
const ResetPasswordFinish = () => import('../account/reset-password/finish/reset-password-finish.vue');
const ChangePassword = () => import('../account/change-password/change-password.vue');
const Settings = () => import('../account/settings/settings.vue');
const JhiUserManagementComponent = () => import('../admin/user-management/user-management.vue');
const JhiUserManagementViewComponent = () => import('../admin/user-management/user-management-view.vue');
const JhiUserManagementEditComponent = () => import('../admin/user-management/user-management-edit.vue');
const JhiConfigurationComponent = () => import('../admin/configuration/configuration.vue');
const JhiDocsComponent = () => import('../admin/docs/docs.vue');
const JhiHealthComponent = () => import('../admin/health/health.vue');
const JhiLogsComponent = () => import('../admin/logs/logs.vue');
const JhiAuditsComponent = () => import('../admin/audits/audits.vue');
const JhiMetricsComponent = () => import('../admin/metrics/metrics.vue');
/* tslint:disable */
// prettier-ignore
const EmployeeInformation = () => import('../entities/employee-information/employee-information.vue');
// prettier-ignore
const EmployeeInformationUpdate = () => import('../entities/employee-information/employee-information-update.vue');
// prettier-ignore
const EmployeeInformationDetails = () => import('../entities/employee-information/employee-information-details.vue');
// prettier-ignore
const EmployeeSalary = () => import('../entities/employee-salary/employee-salary.vue');
// prettier-ignore
const EmployeeSalaryUpdate = () => import('../entities/employee-salary/employee-salary-update.vue');
// prettier-ignore
const EmployeeSalaryDetails = () => import('../entities/employee-salary/employee-salary-details.vue');
// prettier-ignore
const Employee = () => import('../entities/employee/employee.vue');
// prettier-ignore
const EmployeeUpdate = () => import('../entities/employee/employee-update.vue');
// prettier-ignore
const EmployeeDetails = () => import('../entities/employee/employee-details.vue');
// prettier-ignore
const PayrollGenerate = () => import('../entities/payroll-generate/payroll-generate.vue');
// prettier-ignore
const PayrollGenerateUpdate = () => import('../entities/payroll-generate/payroll-generate-update.vue');
// prettier-ignore
const PayrollGenerateDetails = () => import('../entities/payroll-generate/payroll-generate-details.vue');
// prettier-ignore
const Attendance = () => import('../entities/attendance/attendance.vue');
// prettier-ignore
const AttendanceUpdate = () => import('../entities/attendance/attendance-update.vue');
// prettier-ignore
const AttendanceDetails = () => import('../entities/attendance/attendance-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

Vue.use(Router);

// prettier-ignore
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/forbidden',
      name: 'Forbidden',
      component: Error,
      meta: { error403: true }
    },
    {
      path: '/not-found',
      name: 'NotFound',
      component: Error,
      meta: { error404: true }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/account/activate',
      name: 'Activate',
      component: Activate
    },
    {
      path: '/account/reset/request',
      name: 'ResetPasswordInit',
      component: ResetPasswordInit
    },
    {
      path: '/account/reset/finish',
      name: 'ResetPasswordFinish',
      component: ResetPasswordFinish
    },
    {
      path: '/account/password',
      name: 'ChangePassword',
      component: ChangePassword,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/account/settings',
      name: 'Settings',
      component: Settings,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/admin/user-management',
      name: 'JhiUser',
      component: JhiUserManagementComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/new',
      name: 'JhiUserCreate',
      component: JhiUserManagementEditComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/:userId/edit',
      name: 'JhiUserEdit',
      component: JhiUserManagementEditComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/user-management/:userId/view',
      name: 'JhiUserView',
      component: JhiUserManagementViewComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/docs',
      name: 'JhiDocsComponent',
      component: JhiDocsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/audits',
      name: 'JhiAuditsComponent',
      component: JhiAuditsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-health',
      name: 'JhiHealthComponent',
      component: JhiHealthComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/logs',
      name: 'JhiLogsComponent',
      component: JhiLogsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-metrics',
      name: 'JhiMetricsComponent',
      component: JhiMetricsComponent,
      meta: { authorities: [Authority.ADMIN] }
    },
    {
      path: '/admin/jhi-configuration',
      name: 'JhiConfigurationComponent',
      component: JhiConfigurationComponent,
      meta: { authorities: [Authority.ADMIN] }
    }
    ,
    {
      path: '/employee-information',
      name: 'EmployeeInformation',
      component: EmployeeInformation,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/employee-information/new',
      name: 'EmployeeInformationCreate',
      component: EmployeeInformationUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/employee-information/:employeeInformationId/edit',
      name: 'EmployeeInformationEdit',
      component: EmployeeInformationUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/employee-information/:employeeInformationId/view',
      name: 'EmployeeInformationView',
      component: EmployeeInformationDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/employee-salary',
      name: 'EmployeeSalary',
      component: EmployeeSalary,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/employee-salary/new',
      name: 'EmployeeSalaryCreate',
      component: EmployeeSalaryUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/employee-salary/:employeeSalaryId/edit',
      name: 'EmployeeSalaryEdit',
      component: EmployeeSalaryUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/employee-salary/:employeeSalaryId/view',
      name: 'EmployeeSalaryView',
      component: EmployeeSalaryDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/employee',
      name: 'Employee',
      component: Employee,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/employee/new',
      name: 'EmployeeCreate',
      component: EmployeeUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/employee/:employeeId/edit',
      name: 'EmployeeEdit',
      component: EmployeeUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/employee/:employeeId/view',
      name: 'EmployeeView',
      component: EmployeeDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/payroll-generate',
      name: 'PayrollGenerate',
      component: PayrollGenerate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/payroll-generate/new',
      name: 'PayrollGenerateCreate',
      component: PayrollGenerateUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/payroll-generate/:payrollGenerateId/edit',
      name: 'PayrollGenerateEdit',
      component: PayrollGenerateUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/payroll-generate/:payrollGenerateId/view',
      name: 'PayrollGenerateView',
      component: PayrollGenerateDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/attendance',
      name: 'Attendance',
      component: Attendance,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/attendance/new',
      name: 'AttendanceCreate',
      component: AttendanceUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/attendance/:attendanceId/edit',
      name: 'AttendanceEdit',
      component: AttendanceUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/attendance/:attendanceId/view',
      name: 'AttendanceView',
      component: AttendanceDetails,
      meta: { authorities: [Authority.USER] }
    }
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ]
});
