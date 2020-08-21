<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.salesVoucherType.home.title')" id="sales-voucher-type-heading">Sales Voucher Types</span>
            <router-link :to="{name: 'SalesVoucherTypeCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-sales-voucher-type">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.salesVoucherType.home.createLabel')">
                    Create a new Sales Voucher Type
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
        <div class="alert alert-warning" v-if="!isFetching && salesVoucherTypes && salesVoucherTypes.length === 0">
            <span v-text="$t('smartproApp.salesVoucherType.home.notFound')">No salesVoucherTypes found</span>
        </div>
        <div class="table-responsive" v-if="salesVoucherTypes && salesVoucherTypes.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('item')"><span v-text="$t('smartproApp.salesVoucherType.item')">Item</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'item'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('quantity')"><span v-text="$t('smartproApp.salesVoucherType.quantity')">Quantity</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'quantity'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('rate')"><span v-text="$t('smartproApp.salesVoucherType.rate')">Rate</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'rate'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('amount')"><span v-text="$t('smartproApp.salesVoucherType.amount')">Amount</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amount'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extra')"><span v-text="$t('smartproApp.salesVoucherType.extra')">Extra</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extra'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('accountingVoucherId')"><span v-text="$t('smartproApp.salesVoucherType.accountingVoucher')">Accounting Voucher</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'accountingVoucherId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="salesVoucherType in salesVoucherTypes"
                    :key="salesVoucherType.id">
                    <td>
                        <router-link :to="{name: 'SalesVoucherTypeView', params: {salesVoucherTypeId: salesVoucherType.id}}">{{salesVoucherType.id}}</router-link>
                    </td>
                    <td>{{salesVoucherType.item}}</td>
                    <td>{{salesVoucherType.quantity}}</td>
                    <td>{{salesVoucherType.rate}}</td>
                    <td>{{salesVoucherType.amount}}</td>
                    <td>{{salesVoucherType.extra}}</td>
                    <td>
                        <div v-if="salesVoucherType.accountingVoucherId">
                            <router-link :to="{name: 'AccountingVoucherView', params: {accountingVoucherId: salesVoucherType.accountingVoucherId}}">{{salesVoucherType.accountingVoucherId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SalesVoucherTypeView', params: {salesVoucherTypeId: salesVoucherType.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'SalesVoucherTypeEdit', params: {salesVoucherTypeId: salesVoucherType.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(salesVoucherType)"
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
            <span slot="modal-title"><span id="smartproApp.salesVoucherType.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-salesVoucherType-heading" v-text="$t('smartproApp.salesVoucherType.delete.question', {'id': removeId})">Are you sure you want to delete this Sales Voucher Type?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-salesVoucherType" v-text="$t('entity.action.delete')" v-on:click="removeSalesVoucherType()">Delete</button>
            </div>
        </b-modal>
        <div v-show="salesVoucherTypes && salesVoucherTypes.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sales-voucher-type.component.ts">
</script>
