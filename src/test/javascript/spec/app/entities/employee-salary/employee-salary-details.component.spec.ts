/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import EmployeeSalaryDetailComponent from '@/entities/employee-salary/employee-salary-details.vue';
import EmployeeSalaryClass from '@/entities/employee-salary/employee-salary-details.component';
import EmployeeSalaryService from '@/entities/employee-salary/employee-salary.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('EmployeeSalary Management Detail Component', () => {
    let wrapper: Wrapper<EmployeeSalaryClass>;
    let comp: EmployeeSalaryClass;
    let employeeSalaryServiceStub: SinonStubbedInstance<EmployeeSalaryService>;

    beforeEach(() => {
      employeeSalaryServiceStub = sinon.createStubInstance<EmployeeSalaryService>(EmployeeSalaryService);

      wrapper = shallowMount<EmployeeSalaryClass>(EmployeeSalaryDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { employeeSalaryService: () => employeeSalaryServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundEmployeeSalary = { id: 123 };
        employeeSalaryServiceStub.find.resolves(foundEmployeeSalary);

        // WHEN
        comp.retrieveEmployeeSalary(123);
        await comp.$nextTick();

        // THEN
        expect(comp.employeeSalary).toBe(foundEmployeeSalary);
      });
    });
  });
});
