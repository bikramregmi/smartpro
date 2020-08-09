<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.groups.home.title')" id="groups-heading">Groups</span>
            <router-link :to="{name: 'GroupsCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-groups">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.groups.home.createLabel')">
                    Create a new Groups
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
        <div class="alert alert-warning" v-if="!isFetching && groups && groups.length === 0">
            <span v-text="$t('smartproApp.groups.home.notFound')">No groups found</span>
        </div>
        <div class="table-responsive" v-if="groups && groups.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('smartproApp.groups.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('group')"><span v-text="$t('smartproApp.groups.group')">Group</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'group'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField1')"><span v-text="$t('smartproApp.groups.extraField1')">Extra Field 1</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField1'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField2')"><span v-text="$t('smartproApp.groups.extraField2')">Extra Field 2</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField2'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField3')"><span v-text="$t('smartproApp.groups.extraField3')">Extra Field 3</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField3'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField4')"><span v-text="$t('smartproApp.groups.extraField4')">Extra Field 4</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField4'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField5')"><span v-text="$t('smartproApp.groups.extraField5')">Extra Field 5</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField5'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extraField6')"><span v-text="$t('smartproApp.groups.extraField6')">Extra Field 6</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extraField6'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="groups in groups"
                    :key="groups.id">
                    <td>
                        <router-link :to="{name: 'GroupsView', params: {groupsId: groups.id}}">{{groups.id}}</router-link>
                    </td>
                    <td>{{groups.name}}</td>
                    <td>{{groups.group}}</td>
                    <td>{{groups.extraField1}}</td>
                    <td>{{groups.extraField2}}</td>
                    <td>{{groups.extraField3}}</td>
                    <td>{{groups.extraField4}}</td>
                    <td>{{groups.extraField5}}</td>
                    <td>{{groups.extraField6}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'GroupsView', params: {groupsId: groups.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'GroupsEdit', params: {groupsId: groups.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(groups)"
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
            <span slot="modal-title"><span id="smartproApp.groups.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-groups-heading" v-text="$t('smartproApp.groups.delete.question', {'id': removeId})">Are you sure you want to delete this Groups?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-groups" v-text="$t('entity.action.delete')" v-on:click="removeGroups()">Delete</button>
            </div>
        </b-modal>
        <div v-show="groups && groups.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./groups.component.ts">
</script>
