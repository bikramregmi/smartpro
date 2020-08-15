<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.accountingVoucher.home.title')" id="accounting-voucher-heading">Accounting Vouchers</span>
            <router-link :to="{name: 'AccountingVoucherCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-accounting-voucher">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.accountingVoucher.home.createLabel')">
                    Create a new Accounting Voucher
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
        <div class="alert alert-warning" v-if="!isFetching && accountingVouchers && accountingVouchers.length === 0">
            <span v-text="$t('smartproApp.accountingVoucher.home.notFound')">No accountingVouchers found</span>
        </div>
        <div class="table-responsive" v-if="accountingVouchers && accountingVouchers.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('accountName')"><span v-text="$t('smartproApp.accountingVoucher.accountName')">Account Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'accountName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('currentBalance')"><span v-text="$t('smartproApp.accountingVoucher.currentBalance')">Current Balance</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'currentBalance'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('particulars')"><span v-text="$t('smartproApp.accountingVoucher.particulars')">Particulars</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'particulars'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('amount')"><span v-text="$t('smartproApp.accountingVoucher.amount')">Amount</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('narration')"><span v-text="$t('smartproApp.accountingVoucher.narration')">Narration</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'narration'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('total')"><span v-text="$t('smartproApp.accountingVoucher.total')">Total</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'total'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('grandTotal')"><span v-text="$t('smartproApp.accountingVoucher.grandTotal')">Grand Total</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'grandTotal'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="accountingVoucher in accountingVouchers"
                    :key="accountingVoucher.id">
                    <td>
                        <router-link :to="{name: 'AccountingVoucherView', params: {accountingVoucherId: accountingVoucher.id}}">{{accountingVoucher.id}}</router-link>
                    </td>
                    <td>{{accountingVoucher.accountName}}</td>
                    <td>{{accountingVoucher.currentBalance}}</td>
                    <td>{{accountingVoucher.particulars}}</td>
                    <td>{{accountingVoucher.amount}}</td>
                    <td>{{accountingVoucher.narration}}</td>
                    <td>{{accountingVoucher.total}}</td>
                    <td>{{accountingVoucher.grandTotal}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'AccountingVoucherView', params: {accountingVoucherId: accountingVoucher.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'AccountingVoucherEdit', params: {accountingVoucherId: accountingVoucher.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(accountingVoucher)"
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
            <span slot="modal-title"><span id="smartproApp.accountingVoucher.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-accountingVoucher-heading" v-text="$t('smartproApp.accountingVoucher.delete.question', {'id': removeId})">Are you sure you want to delete this Accounting Voucher?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-accountingVoucher" v-text="$t('entity.action.delete')" v-on:click="removeAccountingVoucher()">Delete</button>
            </div>
        </b-modal>
        <div v-show="accountingVouchers && accountingVouchers.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./accounting-voucher.component.ts">
</script>
