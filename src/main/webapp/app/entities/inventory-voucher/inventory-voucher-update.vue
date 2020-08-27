<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.inventoryVoucher.home.createOrEditLabel" v-text="$t('smartproApp.inventoryVoucher.home.createOrEditLabel')">Create or edit a InventoryVoucher</h2>
                <div>
                    <div class="form-group" v-if="inventoryVoucher.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="inventoryVoucher.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.srItem')" for="inventory-voucher-srItem">Sr Item</label>
                        <input type="text" class="form-control" name="srItem" id="inventory-voucher-srItem"
                            :class="{'valid': !$v.inventoryVoucher.srItem.$invalid, 'invalid': $v.inventoryVoucher.srItem.$invalid }" v-model="$v.inventoryVoucher.srItem.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.srQuantity')" for="inventory-voucher-srQuantity">Sr Quantity</label>
                        <input type="number" class="form-control" name="srQuantity" id="inventory-voucher-srQuantity"
                            :class="{'valid': !$v.inventoryVoucher.srQuantity.$invalid, 'invalid': $v.inventoryVoucher.srQuantity.$invalid }" v-model.number="$v.inventoryVoucher.srQuantity.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.srRate')" for="inventory-voucher-srRate">Sr Rate</label>
                        <input type="number" class="form-control" name="srRate" id="inventory-voucher-srRate"
                            :class="{'valid': !$v.inventoryVoucher.srRate.$invalid, 'invalid': $v.inventoryVoucher.srRate.$invalid }" v-model.number="$v.inventoryVoucher.srRate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.srAmount')" for="inventory-voucher-srAmount">Sr Amount</label>
                        <input type="number" class="form-control" name="srAmount" id="inventory-voucher-srAmount"
                            :class="{'valid': !$v.inventoryVoucher.srAmount.$invalid, 'invalid': $v.inventoryVoucher.srAmount.$invalid }" v-model.number="$v.inventoryVoucher.srAmount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.narration')" for="inventory-voucher-narration">Narration</label>
                        <input type="text" class="form-control" name="narration" id="inventory-voucher-narration"
                            :class="{'valid': !$v.inventoryVoucher.narration.$invalid, 'invalid': $v.inventoryVoucher.narration.$invalid }" v-model="$v.inventoryVoucher.narration.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.desItem')" for="inventory-voucher-desItem">Des Item</label>
                        <input type="text" class="form-control" name="desItem" id="inventory-voucher-desItem"
                            :class="{'valid': !$v.inventoryVoucher.desItem.$invalid, 'invalid': $v.inventoryVoucher.desItem.$invalid }" v-model="$v.inventoryVoucher.desItem.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.desQuantity')" for="inventory-voucher-desQuantity">Des Quantity</label>
                        <input type="number" class="form-control" name="desQuantity" id="inventory-voucher-desQuantity"
                            :class="{'valid': !$v.inventoryVoucher.desQuantity.$invalid, 'invalid': $v.inventoryVoucher.desQuantity.$invalid }" v-model.number="$v.inventoryVoucher.desQuantity.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.desRate')" for="inventory-voucher-desRate">Des Rate</label>
                        <input type="number" class="form-control" name="desRate" id="inventory-voucher-desRate"
                            :class="{'valid': !$v.inventoryVoucher.desRate.$invalid, 'invalid': $v.inventoryVoucher.desRate.$invalid }" v-model.number="$v.inventoryVoucher.desRate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.desAmount')" for="inventory-voucher-desAmount">Des Amount</label>
                        <input type="number" class="form-control" name="desAmount" id="inventory-voucher-desAmount"
                            :class="{'valid': !$v.inventoryVoucher.desAmount.$invalid, 'invalid': $v.inventoryVoucher.desAmount.$invalid }" v-model.number="$v.inventoryVoucher.desAmount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.inventoryVoucher.extraField')" for="inventory-voucher-extraField">Extra Field</label>
                        <input type="text" class="form-control" name="extraField" id="inventory-voucher-extraField"
                            :class="{'valid': !$v.inventoryVoucher.extraField.$invalid, 'invalid': $v.inventoryVoucher.extraField.$invalid }" v-model="$v.inventoryVoucher.extraField.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.inventoryVoucher.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./inventory-voucher-update.component.ts">
</script>
