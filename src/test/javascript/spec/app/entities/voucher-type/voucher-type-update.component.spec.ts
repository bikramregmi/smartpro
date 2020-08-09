/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import VoucherTypeUpdateComponent from '@/entities/voucher-type/voucher-type-update.vue';
import VoucherTypeClass from '@/entities/voucher-type/voucher-type-update.component';
import VoucherTypeService from '@/entities/voucher-type/voucher-type.service';

import CompanyService from '@/entities/company/company.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('VoucherType Management Update Component', () => {
    let wrapper: Wrapper<VoucherTypeClass>;
    let comp: VoucherTypeClass;
    let voucherTypeServiceStub: SinonStubbedInstance<VoucherTypeService>;

    beforeEach(() => {
      voucherTypeServiceStub = sinon.createStubInstance<VoucherTypeService>(VoucherTypeService);

      wrapper = shallowMount<VoucherTypeClass>(VoucherTypeUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          voucherTypeService: () => voucherTypeServiceStub,

          companyService: () => new CompanyService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.voucherType = entity;
        voucherTypeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(voucherTypeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.voucherType = entity;
        voucherTypeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(voucherTypeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
