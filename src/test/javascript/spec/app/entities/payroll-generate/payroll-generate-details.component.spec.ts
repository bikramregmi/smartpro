/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import PayrollGenerateDetailComponent from '@/entities/payroll-generate/payroll-generate-details.vue';
import PayrollGenerateClass from '@/entities/payroll-generate/payroll-generate-details.component';
import PayrollGenerateService from '@/entities/payroll-generate/payroll-generate.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('PayrollGenerate Management Detail Component', () => {
    let wrapper: Wrapper<PayrollGenerateClass>;
    let comp: PayrollGenerateClass;
    let payrollGenerateServiceStub: SinonStubbedInstance<PayrollGenerateService>;

    beforeEach(() => {
      payrollGenerateServiceStub = sinon.createStubInstance<PayrollGenerateService>(PayrollGenerateService);

      wrapper = shallowMount<PayrollGenerateClass>(PayrollGenerateDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { payrollGenerateService: () => payrollGenerateServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundPayrollGenerate = { id: 123 };
        payrollGenerateServiceStub.find.resolves(foundPayrollGenerate);

        // WHEN
        comp.retrievePayrollGenerate(123);
        await comp.$nextTick();

        // THEN
        expect(comp.payrollGenerate).toBe(foundPayrollGenerate);
      });
    });
  });
});
