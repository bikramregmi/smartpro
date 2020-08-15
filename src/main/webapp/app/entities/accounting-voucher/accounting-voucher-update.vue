<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.accountingVoucher.home.createOrEditLabel" v-text="$t('smartproApp.accountingVoucher.home.createOrEditLabel')">Create or edit a AccountingVoucher</h2>
                <div>
                    <div class="form-group" v-if="accountingVoucher.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="accountingVoucher.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.accountingVoucher.accountName')" for="accounting-voucher-accountName">Account Name</label>
                        <input type="text" class="form-control" name="accountName" id="accounting-voucher-accountName"
                            :class="{'valid': !$v.accountingVoucher.accountName.$invalid, 'invalid': $v.accountingVoucher.accountName.$invalid }" v-model="$v.accountingVoucher.accountName.$model"  required/>
                        <div v-if="$v.accountingVoucher.accountName.$anyDirty && $v.accountingVoucher.accountName.$invalid">
                            <small class="form-text text-danger" v-if="!$v.accountingVoucher.accountName.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.accountingVoucher.currentBalance')" for="accounting-voucher-currentBalance">Current Balance</label>
                        <input type="text" class="form-control" name="currentBalance" id="accounting-voucher-currentBalance"
                            :class="{'valid': !$v.accountingVoucher.currentBalance.$invalid, 'invalid': $v.accountingVoucher.currentBalance.$invalid }" v-model="$v.accountingVoucher.currentBalance.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.accountingVoucher.particulars')" for="accounting-voucher-particulars">Particulars</label>
                        <input type="text" class="form-control" name="particulars" id="accounting-voucher-particulars"
                            :class="{'valid': !$v.accountingVoucher.particulars.$invalid, 'invalid': $v.accountingVoucher.particulars.$invalid }" v-model="$v.accountingVoucher.particulars.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.accountingVoucher.amount')" for="accounting-voucher-amount">Amount</label>
                        <input type="number" class="form-control" name="amount" id="accounting-voucher-amount"
                            :class="{'valid': !$v.accountingVoucher.amount.$invalid, 'invalid': $v.accountingVoucher.amount.$invalid }" v-model.number="$v.accountingVoucher.amount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.accountingVoucher.narration')" for="accounting-voucher-narration">Narration</label>
                        <input type="text" class="form-control" name="narration" id="accounting-voucher-narration"
                            :class="{'valid': !$v.accountingVoucher.narration.$invalid, 'invalid': $v.accountingVoucher.narration.$invalid }" v-model="$v.accountingVoucher.narration.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.accountingVoucher.total')" for="accounting-voucher-total">Total</label>
                        <input type="text" class="form-control" name="total" id="accounting-voucher-total"
                            :class="{'valid': !$v.accountingVoucher.total.$invalid, 'invalid': $v.accountingVoucher.total.$invalid }" v-model="$v.accountingVoucher.total.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.accountingVoucher.grandTotal')" for="accounting-voucher-grandTotal">Grand Total</label>
                        <input type="number" class="form-control" name="grandTotal" id="accounting-voucher-grandTotal"
                            :class="{'valid': !$v.accountingVoucher.grandTotal.$invalid, 'invalid': $v.accountingVoucher.grandTotal.$invalid }" v-model.number="$v.accountingVoucher.grandTotal.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.accountingVoucher.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./accounting-voucher-update.component.ts">
</script>
