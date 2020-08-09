/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import VoucherTypeDetailComponent from '@/entities/voucher-type/voucher-type-details.vue';
import VoucherTypeClass from '@/entities/voucher-type/voucher-type-details.component';
import VoucherTypeService from '@/entities/voucher-type/voucher-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('VoucherType Management Detail Component', () => {
    let wrapper: Wrapper<VoucherTypeClass>;
    let comp: VoucherTypeClass;
    let voucherTypeServiceStub: SinonStubbedInstance<VoucherTypeService>;

    beforeEach(() => {
      voucherTypeServiceStub = sinon.createStubInstance<VoucherTypeService>(VoucherTypeService);

      wrapper = shallowMount<VoucherTypeClass>(VoucherTypeDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { voucherTypeService: () => voucherTypeServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundVoucherType = { id: 123 };
        voucherTypeServiceStub.find.resolves(foundVoucherType);

        // WHEN
        comp.retrieveVoucherType(123);
        await comp.$nextTick();

        // THEN
        expect(comp.voucherType).toBe(foundVoucherType);
      });
    });
  });
});
