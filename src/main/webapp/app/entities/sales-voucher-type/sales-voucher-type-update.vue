<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.salesVoucherType.home.createOrEditLabel" v-text="$t('smartproApp.salesVoucherType.home.createOrEditLabel')">Create or edit a SalesVoucherType</h2>
                <div>
                    <div class="form-group" v-if="salesVoucherType.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="salesVoucherType.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherType.item')" for="sales-voucher-type-item">Item</label>
                        <input type="text" class="form-control" name="item" id="sales-voucher-type-item"
                            :class="{'valid': !$v.salesVoucherType.item.$invalid, 'invalid': $v.salesVoucherType.item.$invalid }" v-model="$v.salesVoucherType.item.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherType.quantity')" for="sales-voucher-type-quantity">Quantity</label>
                        <input type="number" class="form-control" name="quantity" id="sales-voucher-type-quantity"
                            :class="{'valid': !$v.salesVoucherType.quantity.$invalid, 'invalid': $v.salesVoucherType.quantity.$invalid }" v-model.number="$v.salesVoucherType.quantity.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherType.rate')" for="sales-voucher-type-rate">Rate</label>
                        <input type="number" class="form-control" name="rate" id="sales-voucher-type-rate"
                            :class="{'valid': !$v.salesVoucherType.rate.$invalid, 'invalid': $v.salesVoucherType.rate.$invalid }" v-model.number="$v.salesVoucherType.rate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherType.amount')" for="sales-voucher-type-amount">Amount</label>
                        <input type="number" class="form-control" name="amount" id="sales-voucher-type-amount"
                            :class="{'valid': !$v.salesVoucherType.amount.$invalid, 'invalid': $v.salesVoucherType.amount.$invalid }" v-model.number="$v.salesVoucherType.amount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherType.extra')" for="sales-voucher-type-extra">Extra</label>
                        <input type="text" class="form-control" name="extra" id="sales-voucher-type-extra"
                            :class="{'valid': !$v.salesVoucherType.extra.$invalid, 'invalid': $v.salesVoucherType.extra.$invalid }" v-model="$v.salesVoucherType.extra.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.salesVoucherType.accountingVoucher')" for="sales-voucher-type-accountingVoucher">Accounting Voucher</label>
                        <select class="form-control" id="sales-voucher-type-accountingVoucher" name="accountingVoucher" v-model="salesVoucherType.accountingVoucherId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="accountingVoucherOption.id" v-for="accountingVoucherOption in accountingVouchers" :key="accountingVoucherOption.id">{{accountingVoucherOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.salesVoucherType.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sales-voucher-type-update.component.ts">
</script>
