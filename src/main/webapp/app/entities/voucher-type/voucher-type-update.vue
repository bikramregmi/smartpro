<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.voucherType.home.createOrEditLabel" v-text="$t('smartproApp.voucherType.home.createOrEditLabel')">Create or edit a VoucherType</h2>
                <div>
                    <div class="form-group" v-if="voucherType.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="voucherType.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.voucherType.name')" for="voucher-type-name">Name</label>
                        <input type="text" class="form-control" name="name" id="voucher-type-name"
                            :class="{'valid': !$v.voucherType.name.$invalid, 'invalid': $v.voucherType.name.$invalid }" v-model="$v.voucherType.name.$model"  required/>
                        <div v-if="$v.voucherType.name.$anyDirty && $v.voucherType.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.voucherType.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.voucherType.type')" for="voucher-type-type">Type</label>
                        <input type="text" class="form-control" name="type" id="voucher-type-type"
                            :class="{'valid': !$v.voucherType.type.$invalid, 'invalid': $v.voucherType.type.$invalid }" v-model="$v.voucherType.type.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.voucherType.method')" for="voucher-type-method">Method</label>
                        <input type="text" class="form-control" name="method" id="voucher-type-method"
                            :class="{'valid': !$v.voucherType.method.$invalid, 'invalid': $v.voucherType.method.$invalid }" v-model="$v.voucherType.method.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.voucherType.description')" for="voucher-type-description">Description</label>
                        <input type="text" class="form-control" name="description" id="voucher-type-description"
                            :class="{'valid': !$v.voucherType.description.$invalid, 'invalid': $v.voucherType.description.$invalid }" v-model="$v.voucherType.description.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.voucherType.extraField')" for="voucher-type-extraField">Extra Field</label>
                        <input type="text" class="form-control" name="extraField" id="voucher-type-extraField"
                            :class="{'valid': !$v.voucherType.extraField.$invalid, 'invalid': $v.voucherType.extraField.$invalid }" v-model="$v.voucherType.extraField.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.voucherType.company')" for="voucher-type-company">Company</label>
                        <select class="form-control" id="voucher-type-company" name="company" v-model="voucherType.companyId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="companyOption.id" v-for="companyOption in companies" :key="companyOption.id">{{companyOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.voucherType.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./voucher-type-update.component.ts">
</script>
