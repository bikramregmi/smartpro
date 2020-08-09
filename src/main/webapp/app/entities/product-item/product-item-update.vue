<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.productItem.home.createOrEditLabel" v-text="$t('smartproApp.productItem.home.createOrEditLabel')">Create or edit a ProductItem</h2>
                <div>
                    <div class="form-group" v-if="productItem.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="productItem.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productItem.name')" for="product-item-name">Name</label>
                        <input type="text" class="form-control" name="name" id="product-item-name"
                            :class="{'valid': !$v.productItem.name.$invalid, 'invalid': $v.productItem.name.$invalid }" v-model="$v.productItem.name.$model"  required/>
                        <div v-if="$v.productItem.name.$anyDirty && $v.productItem.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.productItem.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productItem.group')" for="product-item-group">Group</label>
                        <input type="text" class="form-control" name="group" id="product-item-group"
                            :class="{'valid': !$v.productItem.group.$invalid, 'invalid': $v.productItem.group.$invalid }" v-model="$v.productItem.group.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productItem.units')" for="product-item-units">Units</label>
                        <input type="text" class="form-control" name="units" id="product-item-units"
                            :class="{'valid': !$v.productItem.units.$invalid, 'invalid': $v.productItem.units.$invalid }" v-model="$v.productItem.units.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productItem.rate')" for="product-item-rate">Rate</label>
                        <input type="text" class="form-control" name="rate" id="product-item-rate"
                            :class="{'valid': !$v.productItem.rate.$invalid, 'invalid': $v.productItem.rate.$invalid }" v-model="$v.productItem.rate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productItem.quantityPerRate')" for="product-item-quantityPerRate">Quantity Per Rate</label>
                        <input type="text" class="form-control" name="quantityPerRate" id="product-item-quantityPerRate"
                            :class="{'valid': !$v.productItem.quantityPerRate.$invalid, 'invalid': $v.productItem.quantityPerRate.$invalid }" v-model="$v.productItem.quantityPerRate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productItem.value')" for="product-item-value">Value</label>
                        <input type="text" class="form-control" name="value" id="product-item-value"
                            :class="{'valid': !$v.productItem.value.$invalid, 'invalid': $v.productItem.value.$invalid }" v-model="$v.productItem.value.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productItem.extraField')" for="product-item-extraField">Extra Field</label>
                        <input type="text" class="form-control" name="extraField" id="product-item-extraField"
                            :class="{'valid': !$v.productItem.extraField.$invalid, 'invalid': $v.productItem.extraField.$invalid }" v-model="$v.productItem.extraField.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productItem.company')" for="product-item-company">Company</label>
                        <select class="form-control" id="product-item-company" name="company" v-model="productItem.companyId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="companyOption.id" v-for="companyOption in companies" :key="companyOption.id">{{companyOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.productItem.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./product-item-update.component.ts">
</script>
