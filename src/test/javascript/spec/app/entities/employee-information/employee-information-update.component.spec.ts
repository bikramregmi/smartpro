/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import EmployeeInformationUpdateComponent from '@/entities/employee-information/employee-information-update.vue';
import EmployeeInformationClass from '@/entities/employee-information/employee-information-update.component';
import EmployeeInformationService from '@/entities/employee-information/employee-information.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('EmployeeInformation Management Update Component', () => {
    let wrapper: Wrapper<EmployeeInformationClass>;
    let comp: EmployeeInformationClass;
    let employeeInformationServiceStub: SinonStubbedInstance<EmployeeInformationService>;

    beforeEach(() => {
      employeeInformationServiceStub = sinon.createStubInstance<EmployeeInformationService>(EmployeeInformationService);

      wrapper = shallowMount<EmployeeInformationClass>(EmployeeInformationUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          employeeInformationService: () => employeeInformationServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.employeeInformation = entity;
        employeeInformationServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(employeeInformationServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.employeeInformation = entity;
        employeeInformationServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(employeeInformationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
