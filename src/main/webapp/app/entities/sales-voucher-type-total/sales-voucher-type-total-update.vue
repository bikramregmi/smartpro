<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.salesVoucherTypeTotal.home.createOrEditLabel" v-text="$t('smartproApp.salesVoucherTypeTotal.home.createOrEditLabel')">Create or edit a SalesVoucherTypeTotal</h2>
                <div>
                    <div class="form-group" v-if="salesVoucherTypeTotal.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="salesVoucherTypeTotal.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherTypeTotal.item')" for="sales-voucher-type-total-item">Item</label>
                        <input type="text" class="form-control" name="item" id="sales-voucher-type-total-item"
                            :class="{'valid': !$v.salesVoucherTypeTotal.item.$invalid, 'invalid': $v.salesVoucherTypeTotal.item.$invalid }" v-model="$v.salesVoucherTypeTotal.item.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherTypeTotal.quantityTotal')" for="sales-voucher-type-total-quantityTotal">Quantity Total</label>
                        <input type="text" class="form-control" name="quantityTotal" id="sales-voucher-type-total-quantityTotal"
                            :class="{'valid': !$v.salesVoucherTypeTotal.quantityTotal.$invalid, 'invalid': $v.salesVoucherTypeTotal.quantityTotal.$invalid }" v-model="$v.salesVoucherTypeTotal.quantityTotal.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherTypeTotal.rateTotal')" for="sales-voucher-type-total-rateTotal">Rate Total</label>
                        <input type="number" class="form-control" name="rateTotal" id="sales-voucher-type-total-rateTotal"
                            :class="{'valid': !$v.salesVoucherTypeTotal.rateTotal.$invalid, 'invalid': $v.salesVoucherTypeTotal.rateTotal.$invalid }" v-model.number="$v.salesVoucherTypeTotal.rateTotal.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherTypeTotal.amountTotal')" for="sales-voucher-type-total-amountTotal">Amount Total</label>
                        <input type="number" class="form-control" name="amountTotal" id="sales-voucher-type-total-amountTotal"
                            :class="{'valid': !$v.salesVoucherTypeTotal.amountTotal.$invalid, 'invalid': $v.salesVoucherTypeTotal.amountTotal.$invalid }" v-model.number="$v.salesVoucherTypeTotal.amountTotal.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherTypeTotal.referenceNumber')" for="sales-voucher-type-total-referenceNumber">Reference Number</label>
                        <input type="text" class="form-control" name="referenceNumber" id="sales-voucher-type-total-referenceNumber"
                            :class="{'valid': !$v.salesVoucherTypeTotal.referenceNumber.$invalid, 'invalid': $v.salesVoucherTypeTotal.referenceNumber.$invalid }" v-model="$v.salesVoucherTypeTotal.referenceNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherTypeTotal.extraField')" for="sales-voucher-type-total-extraField">Extra Field</label>
                        <input type="text" class="form-control" name="extraField" id="sales-voucher-type-total-extraField"
                            :class="{'valid': !$v.salesVoucherTypeTotal.extraField.$invalid, 'invalid': $v.salesVoucherTypeTotal.extraField.$invalid }" v-model="$v.salesVoucherTypeTotal.extraField.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.salesVoucherTypeTotal.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sales-voucher-type-total-update.component.ts">
</script>
