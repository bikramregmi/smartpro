/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import EmployeeSalaryUpdateComponent from '@/entities/employee-salary/employee-salary-update.vue';
import EmployeeSalaryClass from '@/entities/employee-salary/employee-salary-update.component';
import EmployeeSalaryService from '@/entities/employee-salary/employee-salary.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('EmployeeSalary Management Update Component', () => {
    let wrapper: Wrapper<EmployeeSalaryClass>;
    let comp: EmployeeSalaryClass;
    let employeeSalaryServiceStub: SinonStubbedInstance<EmployeeSalaryService>;

    beforeEach(() => {
      employeeSalaryServiceStub = sinon.createStubInstance<EmployeeSalaryService>(EmployeeSalaryService);

      wrapper = shallowMount<EmployeeSalaryClass>(EmployeeSalaryUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          employeeSalaryService: () => employeeSalaryServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.employeeSalary = entity;
        employeeSalaryServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(employeeSalaryServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.employeeSalary = entity;
        employeeSalaryServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(employeeSalaryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
