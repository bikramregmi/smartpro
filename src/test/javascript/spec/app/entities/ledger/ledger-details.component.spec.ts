/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import LedgerDetailComponent from '@/entities/ledger/ledger-details.vue';
import LedgerClass from '@/entities/ledger/ledger-details.component';
import LedgerService from '@/entities/ledger/ledger.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Ledger Management Detail Component', () => {
    let wrapper: Wrapper<LedgerClass>;
    let comp: LedgerClass;
    let ledgerServiceStub: SinonStubbedInstance<LedgerService>;

    beforeEach(() => {
      ledgerServiceStub = sinon.createStubInstance<LedgerService>(LedgerService);

      wrapper = shallowMount<LedgerClass>(LedgerDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { ledgerService: () => ledgerServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLedger = { id: 123 };
        ledgerServiceStub.find.resolves(foundLedger);

        // WHEN
        comp.retrieveLedger(123);
        await comp.$nextTick();

        // THEN
        expect(comp.ledger).toBe(foundLedger);
      });
    });
  });
});
