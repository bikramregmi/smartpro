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

const Dashboard = () => import('../core/Dashboard.vue');
// prettier-ignore
const Company = () => import('../entities/company/company.vue');
// prettier-ignore
const CompanyUpdate = () => import('../entities/company/company-update.vue');
// prettier-ignore
const CompanyDetails = () => import('../entities/company/company-details.vue');
// prettier-ignore
const Groups = () => import('../entities/groups/groups.vue');
// prettier-ignore
const GroupsUpdate = () => import('../entities/groups/groups-update.vue');
// prettier-ignore
const GroupsDetails = () => import('../entities/groups/groups-details.vue');
// prettier-ignore
const Ledger = () => import('../entities/ledger/ledger.vue');
// prettier-ignore
const LedgerUpdate = () => import('../entities/ledger/ledger-update.vue');
// prettier-ignore
const LedgerDetails = () => import('../entities/ledger/ledger-details.vue');
// prettier-ignore
const Group = () => import('../entities/group/group.vue');
// prettier-ignore
const GroupUpdate = () => import('../entities/group/group-update.vue');
// prettier-ignore
const GroupDetails = () => import('../entities/group/group-details.vue');
// prettier-ignore
const VoucherType = () => import('../entities/voucher-type/voucher-type.vue');
// prettier-ignore
const VoucherTypeUpdate = () => import('../entities/voucher-type/voucher-type-update.vue');
// prettier-ignore
const VoucherTypeDetails = () => import('../entities/voucher-type/voucher-type-details.vue');
// prettier-ignore
const ProductGroups = () => import('../entities/product-groups/product-groups.vue');
// prettier-ignore
const ProductGroupsUpdate = () => import('../entities/product-groups/product-groups-update.vue');
// prettier-ignore
const ProductGroupsDetails = () => import('../entities/product-groups/product-groups-details.vue');
// prettier-ignore
const ProductItem = () => import('../entities/product-item/product-item.vue');
// prettier-ignore
const ProductItemUpdate = () => import('../entities/product-item/product-item-update.vue');
// prettier-ignore
const ProductItemDetails = () => import('../entities/product-item/product-item-details.vue');
// prettier-ignore
const UnitOfMeasure = () => import('../entities/unit-of-measure/unit-of-measure.vue');
// prettier-ignore
const UnitOfMeasureUpdate = () => import('../entities/unit-of-measure/unit-of-measure-update.vue');
// prettier-ignore
const UnitOfMeasureDetails = () => import('../entities/unit-of-measure/unit-of-measure-details.vue');
// prettier-ignore
const AccountingVoucher = () => import('../entities/accounting-voucher/accounting-voucher.vue');
// prettier-ignore
const AccountingVoucherUpdate = () => import('../entities/accounting-voucher/accounting-voucher-update.vue');
// prettier-ignore
const AccountingVoucherDetails = () => import('../entities/accounting-voucher/accounting-voucher-details.vue');
// prettier-ignore
const SalesVoucherType = () => import('../entities/sales-voucher-type/sales-voucher-type.vue');
// prettier-ignore
const SalesVoucherTypeUpdate = () => import('../entities/sales-voucher-type/sales-voucher-type-update.vue');
// prettier-ignore
const SalesVoucherTypeDetails = () => import('../entities/sales-voucher-type/sales-voucher-type-details.vue');
// prettier-ignore
const SalesVoucherTypeTotal = () => import('../entities/sales-voucher-type-total/sales-voucher-type-total.vue');
// prettier-ignore
const SalesVoucherTypeTotalUpdate = () => import('../entities/sales-voucher-type-total/sales-voucher-type-total-update.vue');
// prettier-ignore
const SalesVoucherTypeTotalDetails = () => import('../entities/sales-voucher-type-total/sales-voucher-type-total-details.vue');
// prettier-ignore
const InventoryVoucher = () => import('../entities/inventory-voucher/inventory-voucher.vue');
// prettier-ignore
const InventoryVoucherUpdate = () => import('../entities/inventory-voucher/inventory-voucher-update.vue');
// prettier-ignore
const InventoryVoucherDetails = () => import('../entities/inventory-voucher/inventory-voucher-details.vue');
// prettier-ignore
const InventoryVoucherEntry = () => import('../entities/inventory-voucher-entry/inventory-voucher-entry.vue');
// prettier-ignore
const InventoryVoucherEntryUpdate = () => import('../entities/inventory-voucher-entry/inventory-voucher-entry-update.vue');
// prettier-ignore
const InventoryVoucherEntryDetails = () => import('../entities/inventory-voucher-entry/inventory-voucher-entry-details.vue');
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
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/company',
      name: 'Company',
      component: Company,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/company/new',
      name: 'CompanyCreate',
      component: CompanyUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/company/:companyId/edit',
      name: 'CompanyEdit',
      component: CompanyUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/company/:companyId/view',
      name: 'CompanyView',
      component: CompanyDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/groups',
      name: 'Groups',
      component: Groups,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/groups/new',
      name: 'GroupsCreate',
      component: GroupsUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/groups/:groupsId/edit',
      name: 'GroupsEdit',
      component: GroupsUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/groups/:groupsId/view',
      name: 'GroupsView',
      component: GroupsDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/ledger',
      name: 'Ledger',
      component: Ledger,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/ledger/new',
      name: 'LedgerCreate',
      component: LedgerUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/ledger/:ledgerId/edit',
      name: 'LedgerEdit',
      component: LedgerUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/ledger/:ledgerId/view',
      name: 'LedgerView',
      component: LedgerDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/group',
      name: 'Group',
      component: Group,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/group/new',
      name: 'GroupCreate',
      component: GroupUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/group/:groupId/edit',
      name: 'GroupEdit',
      component: GroupUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/group/:groupId/view',
      name: 'GroupView',
      component: GroupDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/voucher-type',
      name: 'VoucherType',
      component: VoucherType,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/voucher-type/new',
      name: 'VoucherTypeCreate',
      component: VoucherTypeUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/voucher-type/:voucherTypeId/edit',
      name: 'VoucherTypeEdit',
      component: VoucherTypeUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/voucher-type/:voucherTypeId/view',
      name: 'VoucherTypeView',
      component: VoucherTypeDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/product-groups',
      name: 'ProductGroups',
      component: ProductGroups,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/product-groups/new',
      name: 'ProductGroupsCreate',
      component: ProductGroupsUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/product-groups/:productGroupsId/edit',
      name: 'ProductGroupsEdit',
      component: ProductGroupsUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/product-groups/:productGroupsId/view',
      name: 'ProductGroupsView',
      component: ProductGroupsDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/product-item',
      name: 'ProductItem',
      component: ProductItem,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/product-item/new',
      name: 'ProductItemCreate',
      component: ProductItemUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/product-item/:productItemId/edit',
      name: 'ProductItemEdit',
      component: ProductItemUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/product-item/:productItemId/view',
      name: 'ProductItemView',
      component: ProductItemDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/unit-of-measure',
      name: 'UnitOfMeasure',
      component: UnitOfMeasure,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/unit-of-measure/new',
      name: 'UnitOfMeasureCreate',
      component: UnitOfMeasureUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/unit-of-measure/:unitOfMeasureId/edit',
      name: 'UnitOfMeasureEdit',
      component: UnitOfMeasureUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/unit-of-measure/:unitOfMeasureId/view',
      name: 'UnitOfMeasureView',
      component: UnitOfMeasureDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/accounting-voucher',
      name: 'AccountingVoucher',
      component: AccountingVoucher,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/accounting-voucher/new',
      name: 'AccountingVoucherCreate',
      component: AccountingVoucherUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/accounting-voucher/:accountingVoucherId/edit',
      name: 'AccountingVoucherEdit',
      component: AccountingVoucherUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/accounting-voucher/:accountingVoucherId/view',
      name: 'AccountingVoucherView',
      component: AccountingVoucherDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/sales-voucher-type',
      name: 'SalesVoucherType',
      component: SalesVoucherType,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/sales-voucher-type/new',
      name: 'SalesVoucherTypeCreate',
      component: SalesVoucherTypeUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/sales-voucher-type/:salesVoucherTypeId/edit',
      name: 'SalesVoucherTypeEdit',
      component: SalesVoucherTypeUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/sales-voucher-type/:salesVoucherTypeId/view',
      name: 'SalesVoucherTypeView',
      component: SalesVoucherTypeDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/sales-voucher-type-total',
      name: 'SalesVoucherTypeTotal',
      component: SalesVoucherTypeTotal,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/sales-voucher-type-total/new',
      name: 'SalesVoucherTypeTotalCreate',
      component: SalesVoucherTypeTotalUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/sales-voucher-type-total/:salesVoucherTypeTotalId/edit',
      name: 'SalesVoucherTypeTotalEdit',
      component: SalesVoucherTypeTotalUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/sales-voucher-type-total/:salesVoucherTypeTotalId/view',
      name: 'SalesVoucherTypeTotalView',
      component: SalesVoucherTypeTotalDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/inventory-voucher',
      name: 'InventoryVoucher',
      component: InventoryVoucher,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/inventory-voucher/new',
      name: 'InventoryVoucherCreate',
      component: InventoryVoucherUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/inventory-voucher/:inventoryVoucherId/edit',
      name: 'InventoryVoucherEdit',
      component: InventoryVoucherUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/inventory-voucher/:inventoryVoucherId/view',
      name: 'InventoryVoucherView',
      component: InventoryVoucherDetails,
      meta: { authorities: [Authority.USER] }
    }
    ,
    {
      path: '/inventory-voucher-entry',
      name: 'InventoryVoucherEntry',
      component: InventoryVoucherEntry,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/inventory-voucher-entry/new',
      name: 'InventoryVoucherEntryCreate',
      component: InventoryVoucherEntryUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/inventory-voucher-entry/:inventoryVoucherEntryId/edit',
      name: 'InventoryVoucherEntryEdit',
      component: InventoryVoucherEntryUpdate,
      meta: { authorities: [Authority.USER] }
    },
    {
      path: '/inventory-voucher-entry/:inventoryVoucherEntryId/view',
      name: 'InventoryVoucherEntryView',
      component: InventoryVoucherEntryDetails,
      meta: { authorities: [Authority.USER] }
    }
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ]
});
