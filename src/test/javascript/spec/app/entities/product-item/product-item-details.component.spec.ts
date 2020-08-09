/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ProductItemDetailComponent from '@/entities/product-item/product-item-details.vue';
import ProductItemClass from '@/entities/product-item/product-item-details.component';
import ProductItemService from '@/entities/product-item/product-item.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ProductItem Management Detail Component', () => {
    let wrapper: Wrapper<ProductItemClass>;
    let comp: ProductItemClass;
    let productItemServiceStub: SinonStubbedInstance<ProductItemService>;

    beforeEach(() => {
      productItemServiceStub = sinon.createStubInstance<ProductItemService>(ProductItemService);

      wrapper = shallowMount<ProductItemClass>(ProductItemDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { productItemService: () => productItemServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundProductItem = { id: 123 };
        productItemServiceStub.find.resolves(foundProductItem);

        // WHEN
        comp.retrieveProductItem(123);
        await comp.$nextTick();

        // THEN
        expect(comp.productItem).toBe(foundProductItem);
      });
    });
  });
});
