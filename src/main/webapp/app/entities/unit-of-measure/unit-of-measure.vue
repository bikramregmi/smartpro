<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.unitOfMeasure.home.title')" id="unit-of-measure-heading">Unit Of Measures</span>
            <router-link :to="{name: 'UnitOfMeasureCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-unit-of-measure">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.unitOfMeasure.home.createLabel')">
                    Create a new Unit Of Measure
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
        <div class="alert alert-warning" v-if="!isFetching && unitOfMeasures && unitOfMeasures.length === 0">
            <span v-text="$t('smartproApp.unitOfMeasure.home.notFound')">No unitOfMeasures found</span>
        </div>
        <div class="table-responsive" v-if="unitOfMeasures && unitOfMeasures.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('formalName')"><span v-text="$t('smartproApp.unitOfMeasure.formalName')">Formal Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'formalName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('type')"><span v-text="$t('smartproApp.unitOfMeasure.type')">Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'type'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('symbol')"><span v-text="$t('smartproApp.unitOfMeasure.symbol')">Symbol</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'symbol'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('decimalPlaces')"><span v-text="$t('smartproApp.unitOfMeasure.decimalPlaces')">Decimal Places</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'decimalPlaces'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField')"><span v-text="$t('smartproApp.unitOfMeasure.extraField')">Extra Field</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="unitOfMeasure in unitOfMeasures"
                    :key="unitOfMeasure.id">
                    <td>
                        <router-link :to="{name: 'UnitOfMeasureView', params: {unitOfMeasureId: unitOfMeasure.id}}">{{unitOfMeasure.id}}</router-link>
                    </td>
                    <td>{{unitOfMeasure.formalName}}</td>
                    <td>{{unitOfMeasure.type}}</td>
                    <td>{{unitOfMeasure.symbol}}</td>
                    <td>{{unitOfMeasure.decimalPlaces}}</td>
                    <td>{{unitOfMeasure.extraField}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'UnitOfMeasureView', params: {unitOfMeasureId: unitOfMeasure.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'UnitOfMeasureEdit', params: {unitOfMeasureId: unitOfMeasure.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(unitOfMeasure)"
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
            <span slot="modal-title"><span id="smartproApp.unitOfMeasure.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-unitOfMeasure-heading" v-text="$t('smartproApp.unitOfMeasure.delete.question', {'id': removeId})">Are you sure you want to delete this Unit Of Measure?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-unitOfMeasure" v-text="$t('entity.action.delete')" v-on:click="removeUnitOfMeasure()">Delete</button>
            </div>
        </b-modal>
        <div v-show="unitOfMeasures && unitOfMeasures.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./unit-of-measure.component.ts">
</script>
