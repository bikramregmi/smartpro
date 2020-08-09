<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.productGroups.home.title')" id="product-groups-heading">Product Groups</span>
            <router-link :to="{name: 'ProductGroupsCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-product-groups">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.productGroups.home.createLabel')">
                    Create a new Product Groups
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
        <div class="alert alert-warning" v-if="!isFetching && productGroups && productGroups.length === 0">
            <span v-text="$t('smartproApp.productGroups.home.notFound')">No productGroups found</span>
        </div>
        <div class="table-responsive" v-if="productGroups && productGroups.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('smartproApp.productGroups.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('group')"><span v-text="$t('smartproApp.productGroups.group')">Group</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'group'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('quantity')"><span v-text="$t('smartproApp.productGroups.quantity')">Quantity</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'quantity'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField')"><span v-text="$t('smartproApp.productGroups.extraField')">Extra Field</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('companyId')"><span v-text="$t('smartproApp.productGroups.company')">Company</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'companyId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="productGroups in productGroups"
                    :key="productGroups.id">
                    <td>
                        <router-link :to="{name: 'ProductGroupsView', params: {productGroupsId: productGroups.id}}">{{productGroups.id}}</router-link>
                    </td>
                    <td>{{productGroups.name}}</td>
                    <td>{{productGroups.group}}</td>
                    <td>{{productGroups.quantity}}</td>
                    <td>{{productGroups.extraField}}</td>
                    <td>
                        <div v-if="productGroups.companyId">
                            <router-link :to="{name: 'CompanyView', params: {companyId: productGroups.companyId}}">{{productGroups.companyId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProductGroupsView', params: {productGroupsId: productGroups.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ProductGroupsEdit', params: {productGroupsId: productGroups.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(productGroups)"
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
            <span slot="modal-title"><span id="smartproApp.productGroups.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-productGroups-heading" v-text="$t('smartproApp.productGroups.delete.question', {'id': removeId})">Are you sure you want to delete this Product Groups?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-productGroups" v-text="$t('entity.action.delete')" v-on:click="removeProductGroups()">Delete</button>
            </div>
        </b-modal>
        <div v-show="productGroups && productGroups.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./product-groups.component.ts">
</script>
