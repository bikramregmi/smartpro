<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.company.home.title')" id="company-heading">Companies</span>
            <router-link :to="{name: 'CompanyCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-company">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.company.home.createLabel')">
                    Create a new Company
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
        <div class="alert alert-warning" v-if="!isFetching && companies && companies.length === 0">
            <span v-text="$t('smartproApp.company.home.notFound')">No companies found</span>
        </div>
        <div class="table-responsive" v-if="companies && companies.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('companyName')"><span v-text="$t('smartproApp.company.companyName')">Company Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'companyName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('address')"><span v-text="$t('smartproApp.company.address')">Address</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('email')"><span v-text="$t('smartproApp.company.email')">Email</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('fy')"><span v-text="$t('smartproApp.company.fy')">Fy</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fy'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('bookDate')"><span v-text="$t('smartproApp.company.bookDate')">Book Date</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'bookDate'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('currencyString')"><span v-text="$t('smartproApp.company.currencyString')">Currency String</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'currencyString'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('currencySymbol')"><span v-text="$t('smartproApp.company.currencySymbol')">Currency Symbol</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'currencySymbol'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('currencySubString')"><span v-text="$t('smartproApp.company.currencySubString')">Currency Sub String</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'currencySubString'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('dealerType')"><span v-text="$t('smartproApp.company.dealerType')">Dealer Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'dealerType'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('taxRate')"><span v-text="$t('smartproApp.company.taxRate')">Tax Rate</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'taxRate'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('type')"><span v-text="$t('smartproApp.company.type')">Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'type'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField')"><span v-text="$t('smartproApp.company.extraField')">Extra Field</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extrafield1')"><span v-text="$t('smartproApp.company.extrafield1')">Extrafield 1</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extrafield1'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="company in companies"
                    :key="company.id">
                    <td>
                        <router-link :to="{name: 'CompanyView', params: {companyId: company.id}}">{{company.id}}</router-link>
                    </td>
                    <td>{{company.companyName}}</td>
                    <td>{{company.address}}</td>
                    <td>{{company.email}}</td>
                    <td>{{company.fy}}</td>
                    <td>{{company.bookDate}}</td>
                    <td>{{company.currencyString}}</td>
                    <td>{{company.currencySymbol}}</td>
                    <td>{{company.currencySubString}}</td>
                    <td>{{company.dealerType}}</td>
                    <td>{{company.taxRate}}</td>
                    <td>{{company.type}}</td>
                    <td>{{company.extraField}}</td>
                    <td>{{company.extrafield1}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CompanyView', params: {companyId: company.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'CompanyEdit', params: {companyId: company.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(company)"
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
            <span slot="modal-title"><span id="smartproApp.company.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-company-heading" v-text="$t('smartproApp.company.delete.question', {'id': removeId})">Are you sure you want to delete this Company?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-company" v-text="$t('entity.action.delete')" v-on:click="removeCompany()">Delete</button>
            </div>
        </b-modal>
        <div v-show="companies && companies.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./company.component.ts">
</script>
