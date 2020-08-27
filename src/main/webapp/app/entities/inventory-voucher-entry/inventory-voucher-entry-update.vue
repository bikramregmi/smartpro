<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.inventoryVoucherEntry.home.createOrEditLabel" v-text="$t('smartproApp.inventoryVoucherEntry.home.createOrEditLabel')">Create or edit a InventoryVoucherEntry</h2>
                <div>
                    <div class="form-group" v-if="inventoryVoucherEntry.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="inventoryVoucherEntry.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.srItem')" for="inventory-voucher-entry-srItem">Sr Item</label>
                        <input type="text" class="form-control" name="srItem" id="inventory-voucher-entry-srItem"
                            :class="{'valid': !$v.inventoryVoucherEntry.srItem.$invalid, 'invalid': $v.inventoryVoucherEntry.srItem.$invalid }" v-model="$v.inventoryVoucherEntry.srItem.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.srQuantity')" for="inventory-voucher-entry-srQuantity">Sr Quantity</label>
                        <input type="number" class="form-control" name="srQuantity" id="inventory-voucher-entry-srQuantity"
                            :class="{'valid': !$v.inventoryVoucherEntry.srQuantity.$invalid, 'invalid': $v.inventoryVoucherEntry.srQuantity.$invalid }" v-model.number="$v.inventoryVoucherEntry.srQuantity.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.srRate')" for="inventory-voucher-entry-srRate">Sr Rate</label>
                        <input type="number" class="form-control" name="srRate" id="inventory-voucher-entry-srRate"
                            :class="{'valid': !$v.inventoryVoucherEntry.srRate.$invalid, 'invalid': $v.inventoryVoucherEntry.srRate.$invalid }" v-model.number="$v.inventoryVoucherEntry.srRate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.srAmount')" for="inventory-voucher-entry-srAmount">Sr Amount</label>
                        <input type="number" class="form-control" name="srAmount" id="inventory-voucher-entry-srAmount"
                            :class="{'valid': !$v.inventoryVoucherEntry.srAmount.$invalid, 'invalid': $v.inventoryVoucherEntry.srAmount.$invalid }" v-model.number="$v.inventoryVoucherEntry.srAmount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.desItem')" for="inventory-voucher-entry-desItem">Des Item</label>
                        <input type="text" class="form-control" name="desItem" id="inventory-voucher-entry-desItem"
                            :class="{'valid': !$v.inventoryVoucherEntry.desItem.$invalid, 'invalid': $v.inventoryVoucherEntry.desItem.$invalid }" v-model="$v.inventoryVoucherEntry.desItem.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.desQuantity')" for="inventory-voucher-entry-desQuantity">Des Quantity</label>
                        <input type="number" class="form-control" name="desQuantity" id="inventory-voucher-entry-desQuantity"
                            :class="{'valid': !$v.inventoryVoucherEntry.desQuantity.$invalid, 'invalid': $v.inventoryVoucherEntry.desQuantity.$invalid }" v-model.number="$v.inventoryVoucherEntry.desQuantity.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.desRate')" for="inventory-voucher-entry-desRate">Des Rate</label>
                        <input type="number" class="form-control" name="desRate" id="inventory-voucher-entry-desRate"
                            :class="{'valid': !$v.inventoryVoucherEntry.desRate.$invalid, 'invalid': $v.inventoryVoucherEntry.desRate.$invalid }" v-model.number="$v.inventoryVoucherEntry.desRate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.desAmount')" for="inventory-voucher-entry-desAmount">Des Amount</label>
                        <input type="number" class="form-control" name="desAmount" id="inventory-voucher-entry-desAmount"
                            :class="{'valid': !$v.inventoryVoucherEntry.desAmount.$invalid, 'invalid': $v.inventoryVoucherEntry.desAmount.$invalid }" v-model.number="$v.inventoryVoucherEntry.desAmount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.extraField')" for="inventory-voucher-entry-extraField">Extra Field</label>
                        <input type="text" class="form-control" name="extraField" id="inventory-voucher-entry-extraField"
                            :class="{'valid': !$v.inventoryVoucherEntry.extraField.$invalid, 'invalid': $v.inventoryVoucherEntry.extraField.$invalid }" v-model="$v.inventoryVoucherEntry.extraField.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucherEntry.inventoryVoucher')" for="inventory-voucher-entry-inventoryVoucher">Inventory Voucher</label>
                        <select class="form-control" id="inventory-voucher-entry-inventoryVoucher" name="inventoryVoucher" v-model="inventoryVoucherEntry.inventoryVoucherId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="inventoryVoucherOption.id" v-for="inventoryVoucherOption in inventoryVouchers" :key="inventoryVoucherOption.id">{{inventoryVoucherOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.inventoryVoucherEntry.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./inventory-voucher-entry-update.component.ts">
</script>
