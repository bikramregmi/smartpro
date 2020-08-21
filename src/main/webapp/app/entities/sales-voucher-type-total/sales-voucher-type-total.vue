<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.salesVoucherTypeTotal.home.title')" id="sales-voucher-type-total-heading">Sales Voucher Type Totals</span>
            <router-link :to="{name: 'SalesVoucherTypeTotalCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-sales-voucher-type-total">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.salesVoucherTypeTotal.home.createLabel')">
                    Create a new Sales Voucher Type Total
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
        <div class="alert alert-warning" v-if="!isFetching && salesVoucherTypeTotals && salesVoucherTypeTotals.length === 0">
            <span v-text="$t('smartproApp.salesVoucherTypeTotal.home.notFound')">No salesVoucherTypeTotals found</span>
        </div>
        <div class="table-responsive" v-if="salesVoucherTypeTotals && salesVoucherTypeTotals.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('item')"><span v-text="$t('smartproApp.salesVoucherTypeTotal.item')">Item</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'item'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('quantityTotal')"><span v-text="$t('smartproApp.salesVoucherTypeTotal.quantityTotal')">Quantity Total</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'quantityTotal'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('rateTotal')"><span v-text="$t('smartproApp.salesVoucherTypeTotal.rateTotal')">Rate Total</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'rateTotal'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('amountTotal')"><span v-text="$t('smartproApp.salesVoucherTypeTotal.amountTotal')">Amount Total</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'amountTotal'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('referenceNumber')"><span v-text="$t('smartproApp.salesVoucherTypeTotal.referenceNumber')">Reference Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'referenceNumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField')"><span v-text="$t('smartproApp.salesVoucherTypeTotal.extraField')">Extra Field</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="salesVoucherTypeTotal in salesVoucherTypeTotals"
                    :key="salesVoucherTypeTotal.id">
                    <td>
                        <router-link :to="{name: 'SalesVoucherTypeTotalView', params: {salesVoucherTypeTotalId: salesVoucherTypeTotal.id}}">{{salesVoucherTypeTotal.id}}</router-link>
                    </td>
                    <td>{{salesVoucherTypeTotal.item}}</td>
                    <td>{{salesVoucherTypeTotal.quantityTotal}}</td>
                    <td>{{salesVoucherTypeTotal.rateTotal}}</td>
                    <td>{{salesVoucherTypeTotal.amountTotal}}</td>
                    <td>{{salesVoucherTypeTotal.referenceNumber}}</td>
                    <td>{{salesVoucherTypeTotal.extraField}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SalesVoucherTypeTotalView', params: {salesVoucherTypeTotalId: salesVoucherTypeTotal.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'SalesVoucherTypeTotalEdit', params: {salesVoucherTypeTotalId: salesVoucherTypeTotal.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(salesVoucherTypeTotal)"
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
            <span slot="modal-title"><span id="smartproApp.salesVoucherTypeTotal.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-salesVoucherTypeTotal-heading" v-text="$t('smartproApp.salesVoucherTypeTotal.delete.question', {'id': removeId})">Are you sure you want to delete this Sales Voucher Type Total?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-salesVoucherTypeTotal" v-text="$t('entity.action.delete')" v-on:click="removeSalesVoucherTypeTotal()">Delete</button>
            </div>
        </b-modal>
        <div v-show="salesVoucherTypeTotals && salesVoucherTypeTotals.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sales-voucher-type-total.component.ts">
</script>
