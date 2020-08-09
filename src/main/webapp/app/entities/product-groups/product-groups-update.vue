<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.productGroups.home.createOrEditLabel" v-text="$t('smartproApp.productGroups.home.createOrEditLabel')">Create or edit a ProductGroups</h2>
                <div>
                    <div class="form-group" v-if="productGroups.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="productGroups.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productGroups.name')" for="product-groups-name">Name</label>
                        <input type="text" class="form-control" name="name" id="product-groups-name"
                            :class="{'valid': !$v.productGroups.name.$invalid, 'invalid': $v.productGroups.name.$invalid }" v-model="$v.productGroups.name.$model"  required/>
                        <div v-if="$v.productGroups.name.$anyDirty && $v.productGroups.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.productGroups.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productGroups.group')" for="product-groups-group">Group</label>
                        <input type="text" class="form-control" name="group" id="product-groups-group"
                            :class="{'valid': !$v.productGroups.group.$invalid, 'invalid': $v.productGroups.group.$invalid }" v-model="$v.productGroups.group.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productGroups.quantity')" for="product-groups-quantity">Quantity</label>
                        <input type="text" class="form-control" name="quantity" id="product-groups-quantity"
                            :class="{'valid': !$v.productGroups.quantity.$invalid, 'invalid': $v.productGroups.quantity.$invalid }" v-model="$v.productGroups.quantity.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productGroups.extraField')" for="product-groups-extraField">Extra Field</label>
                        <input type="text" class="form-control" name="extraField" id="product-groups-extraField"
                            :class="{'valid': !$v.productGroups.extraField.$invalid, 'invalid': $v.productGroups.extraField.$invalid }" v-model="$v.productGroups.extraField.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.productGroups.company')" for="product-groups-company">Company</label>
                        <select class="form-control" id="product-groups-company" name="company" v-model="productGroups.companyId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="companyOption.id" v-for="companyOption in companies" :key="companyOption.id">{{companyOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.productGroups.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./product-groups-update.component.ts">
</script>
