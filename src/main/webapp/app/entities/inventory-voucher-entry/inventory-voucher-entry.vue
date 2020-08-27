<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('smartproApp.inventoryVoucherEntry.home.title')" id="inventory-voucher-entry-heading">Inventory Voucher Entries</span>
            <router-link :to="{name: 'InventoryVoucherEntryCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-inventory-voucher-entry">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('smartproApp.inventoryVoucherEntry.home.createLabel')">
                    Create a new Inventory Voucher Entry
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
        <div class="alert alert-warning" v-if="!isFetching && inventoryVoucherEntries && inventoryVoucherEntries.length === 0">
            <span v-text="$t('smartproApp.inventoryVoucherEntry.home.notFound')">No inventoryVoucherEntries found</span>
        </div>
        <div class="table-responsive" v-if="inventoryVoucherEntries && inventoryVoucherEntries.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.srItem')">Sr Item</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.srQuantity')">Sr Quantity</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.srRate')">Sr Rate</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.srAmount')">Sr Amount</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.desItem')">Des Item</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.desQuantity')">Des Quantity</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.desRate')">Des Rate</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.desAmount')">Des Amount</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.extraField')">Extra Field</span></th>
                    <th><span v-text="$t('smartproApp.inventoryVoucherEntry.inventoryVoucher')">Inventory Voucher</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="inventoryVoucherEntry in inventoryVoucherEntries"
                    :key="inventoryVoucherEntry.id">
                    <td>
                        <router-link :to="{name: 'InventoryVoucherEntryView', params: {inventoryVoucherEntryId: inventoryVoucherEntry.id}}">{{inventoryVoucherEntry.id}}</router-link>
                    </td>
                    <td>{{inventoryVoucherEntry.srItem}}</td>
                    <td>{{inventoryVoucherEntry.srQuantity}}</td>
                    <td>{{inventoryVoucherEntry.srRate}}</td>
                    <td>{{inventoryVoucherEntry.srAmount}}</td>
                    <td>{{inventoryVoucherEntry.desItem}}</td>
                    <td>{{inventoryVoucherEntry.desQuantity}}</td>
                    <td>{{inventoryVoucherEntry.desRate}}</td>
                    <td>{{inventoryVoucherEntry.desAmount}}</td>
                    <td>{{inventoryVoucherEntry.extraField}}</td>
                    <td>
                        <div v-if="inventoryVoucherEntry.inventoryVoucherId">
                            <router-link :to="{name: 'InventoryVoucherView', params: {inventoryVoucherId: inventoryVoucherEntry.inventoryVoucherId}}">{{inventoryVoucherEntry.inventoryVoucherId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'InventoryVoucherEntryView', params: {inventoryVoucherEntryId: inventoryVoucherEntry.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'InventoryVoucherEntryEdit', params: {inventoryVoucherEntryId: inventoryVoucherEntry.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(inventoryVoucherEntry)"
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
            <span slot="modal-title"><span id="smartproApp.inventoryVoucherEntry.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-inventoryVoucherEntry-heading" v-text="$t('smartproApp.inventoryVoucherEntry.delete.question', {'id': removeId})">Are you sure you want to delete this Inventory Voucher Entry?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-inventoryVoucherEntry" v-text="$t('entity.action.delete')" v-on:click="removeInventoryVoucherEntry()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./inventory-voucher-entry.component.ts">
</script>
