/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import LedgerUpdateComponent from '@/entities/ledger/ledger-update.vue';
import LedgerClass from '@/entities/ledger/ledger-update.component';
import LedgerService from '@/entities/ledger/ledger.service';

import GroupsService from '@/entities/groups/groups.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Ledger Management Update Component', () => {
    let wrapper: Wrapper<LedgerClass>;
    let comp: LedgerClass;
    let ledgerServiceStub: SinonStubbedInstance<LedgerService>;

    beforeEach(() => {
      ledgerServiceStub = sinon.createStubInstance<LedgerService>(LedgerService);

      wrapper = shallowMount<LedgerClass>(LedgerUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          ledgerService: () => ledgerServiceStub,

          groupsService: () => new GroupsService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.ledger = entity;
        ledgerServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ledgerServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.ledger = entity;
        ledgerServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ledgerServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
