/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import EmployeeInformationDetailComponent from '@/entities/employee-information/employee-information-details.vue';
import EmployeeInformationClass from '@/entities/employee-information/employee-information-details.component';
import EmployeeInformationService from '@/entities/employee-information/employee-information.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('EmployeeInformation Management Detail Component', () => {
    let wrapper: Wrapper<EmployeeInformationClass>;
    let comp: EmployeeInformationClass;
    let employeeInformationServiceStub: SinonStubbedInstance<EmployeeInformationService>;

    beforeEach(() => {
      employeeInformationServiceStub = sinon.createStubInstance<EmployeeInformationService>(EmployeeInformationService);

      wrapper = shallowMount<EmployeeInformationClass>(EmployeeInformationDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { employeeInformationService: () => employeeInformationServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundEmployeeInformation = { id: 123 };
        employeeInformationServiceStub.find.resolves(foundEmployeeInformation);

        // WHEN
        comp.retrieveEmployeeInformation(123);
        await comp.$nextTick();

        // THEN
        expect(comp.employeeInformation).toBe(foundEmployeeInformation);
      });
    });
  });
});
