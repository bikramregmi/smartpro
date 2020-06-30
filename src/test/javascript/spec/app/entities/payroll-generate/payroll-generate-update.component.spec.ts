/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import PayrollGenerateUpdateComponent from '@/entities/payroll-generate/payroll-generate-update.vue';
import PayrollGenerateClass from '@/entities/payroll-generate/payroll-generate-update.component';
import PayrollGenerateService from '@/entities/payroll-generate/payroll-generate.service';

import EmployeeService from '@/entities/employee/employee.service';

import EmployeeSalaryService from '@/entities/employee-salary/employee-salary.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('PayrollGenerate Management Update Component', () => {
    let wrapper: Wrapper<PayrollGenerateClass>;
    let comp: PayrollGenerateClass;
    let payrollGenerateServiceStub: SinonStubbedInstance<PayrollGenerateService>;

    beforeEach(() => {
      payrollGenerateServiceStub = sinon.createStubInstance<PayrollGenerateService>(PayrollGenerateService);

      wrapper = shallowMount<PayrollGenerateClass>(PayrollGenerateUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          payrollGenerateService: () => payrollGenerateServiceStub,

          employeeService: () => new EmployeeService(),

          employeeSalaryService: () => new EmployeeSalaryService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.payrollGenerate = entity;
        payrollGenerateServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(payrollGenerateServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.payrollGenerate = entity;
        payrollGenerateServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(payrollGenerateServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
