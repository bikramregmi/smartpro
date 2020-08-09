<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.ledger.home.title')" id="ledger-heading">Ledgers</span>
            <router-link :to="{name: 'LedgerCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-ledger">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.ledger.home.createLabel')">
                    Create a new Ledger
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && ledgers && ledgers.length === 0">
            <span v-text="$t('smartproApp.ledger.home.notFound')">No ledgers found</span>
        </div>
        <div class="table-responsive" v-if="ledgers && ledgers.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('smartproApp.ledger.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('mailingName')"><span v-text="$t('smartproApp.ledger.mailingName')">Mailing Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mailingName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('mailingAddress')"><span v-text="$t('smartproApp.ledger.mailingAddress')">Mailing Address</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mailingAddress'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('panNo')"><span v-text="$t('smartproApp.ledger.panNo')">Pan No</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'panNo'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField1')"><span v-text="$t('smartproApp.ledger.extraField1')">Extra Field 1</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField1'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField2')"><span v-text="$t('smartproApp.ledger.extraField2')">Extra Field 2</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField2'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField3')"><span v-text="$t('smartproApp.ledger.extraField3')">Extra Field 3</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField3'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField4')"><span v-text="$t('smartproApp.ledger.extraField4')">Extra Field 4</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField4'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField5')"><span v-text="$t('smartproApp.ledger.extraField5')">Extra Field 5</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField5'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('ledgerId')"><span v-text="$t('smartproApp.ledger.ledger')">Ledger</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ledgerId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="ledger in ledgers"
                    :key="ledger.id">
                    <td>
                        <router-link :to="{name: 'LedgerView', params: {ledgerId: ledger.id}}">{{ledger.id}}</router-link>
                    </td>
                    <td>{{ledger.name}}</td>
                    <td>{{ledger.mailingName}}</td>
                    <td>{{ledger.mailingAddress}}</td>
                    <td>{{ledger.panNo}}</td>
                    <td>{{ledger.extraField1}}</td>
                    <td>{{ledger.extraField2}}</td>
                    <td>{{ledger.extraField3}}</td>
                    <td>{{ledger.extraField4}}</td>
                    <td>{{ledger.extraField5}}</td>
                    <td>
                        <div v-if="ledger.ledgerId">
                            <router-link :to="{name: 'LedgerView', params: {ledgerId: ledger.ledgerId}}">{{ledger.ledgerId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'LedgerView', params: {ledgerId: ledger.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'LedgerEdit', params: {ledgerId: ledger.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(ledger)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="smartproApp.ledger.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-ledger-heading" v-text="$t('smartproApp.ledger.delete.question', {'id': removeId})">Are you sure you want to delete this Ledger?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-ledger" v-text="$t('entity.action.delete')" v-on:click="removeLedger()">Delete</button>
            </div>
        </b-modal>
        <div v-show="ledgers && ledgers.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./ledger.component.ts">
</script>
