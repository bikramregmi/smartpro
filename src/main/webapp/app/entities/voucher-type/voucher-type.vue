<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.voucherType.home.title')" id="voucher-type-heading">Voucher Types</span>
            <router-link :to="{name: 'VoucherTypeCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-voucher-type">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.voucherType.home.createLabel')">
                    Create a new Voucher Type
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
        <div class="alert alert-warning" v-if="!isFetching && voucherTypes && voucherTypes.length === 0">
            <span v-text="$t('smartproApp.voucherType.home.notFound')">No voucherTypes found</span>
        </div>
        <div class="table-responsive" v-if="voucherTypes && voucherTypes.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('smartproApp.voucherType.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('type')"><span v-text="$t('smartproApp.voucherType.type')">Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'type'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('method')"><span v-text="$t('smartproApp.voucherType.method')">Method</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'method'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('description')"><span v-text="$t('smartproApp.voucherType.description')">Description</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField')"><span v-text="$t('smartproApp.voucherType.extraField')">Extra Field</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('companyId')"><span v-text="$t('smartproApp.voucherType.company')">Company</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'companyId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="voucherType in voucherTypes"
                    :key="voucherType.id">
                    <td>
                        <router-link :to="{name: 'VoucherTypeView', params: {voucherTypeId: voucherType.id}}">{{voucherType.id}}</router-link>
                    </td>
                    <td>{{voucherType.name}}</td>
                    <td>{{voucherType.type}}</td>
                    <td>{{voucherType.method}}</td>
                    <td>{{voucherType.description}}</td>
                    <td>{{voucherType.extraField}}</td>
                    <td>
                        <div v-if="voucherType.companyId">
                            <router-link :to="{name: 'CompanyView', params: {companyId: voucherType.companyId}}">{{voucherType.companyId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'VoucherTypeView', params: {voucherTypeId: voucherType.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'VoucherTypeEdit', params: {voucherTypeId: voucherType.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(voucherType)"
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
            <span slot="modal-title"><span id="smartproApp.voucherType.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-voucherType-heading" v-text="$t('smartproApp.voucherType.delete.question', {'id': removeId})">Are you sure you want to delete this Voucher Type?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-voucherType" v-text="$t('entity.action.delete')" v-on:click="removeVoucherType()">Delete</button>
            </div>
        </b-modal>
        <div v-show="voucherTypes && voucherTypes.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./voucher-type.component.ts">
</script>
