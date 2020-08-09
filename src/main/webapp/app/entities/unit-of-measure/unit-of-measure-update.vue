<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.unitOfMeasure.home.createOrEditLabel" v-text="$t('smartproApp.unitOfMeasure.home.createOrEditLabel')">Create or edit a UnitOfMeasure</h2>
                <div>
                    <div class="form-group" v-if="unitOfMeasure.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="unitOfMeasure.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.unitOfMeasure.formalName')" for="unit-of-measure-formalName">Formal Name</label>
                        <input type="text" class="form-control" name="formalName" id="unit-of-measure-formalName"
                            :class="{'valid': !$v.unitOfMeasure.formalName.$invalid, 'invalid': $v.unitOfMeasure.formalName.$invalid }" v-model="$v.unitOfMeasure.formalName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.unitOfMeasure.type')" for="unit-of-measure-type">Type</label>
                        <input type="text" class="form-control" name="type" id="unit-of-measure-type"
                            :class="{'valid': !$v.unitOfMeasure.type.$invalid, 'invalid': $v.unitOfMeasure.type.$invalid }" v-model="$v.unitOfMeasure.type.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.unitOfMeasure.symbol')" for="unit-of-measure-symbol">Symbol</label>
                        <input type="text" class="form-control" name="symbol" id="unit-of-measure-symbol"
                            :class="{'valid': !$v.unitOfMeasure.symbol.$invalid, 'invalid': $v.unitOfMeasure.symbol.$invalid }" v-model="$v.unitOfMeasure.symbol.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.unitOfMeasure.decimalPlaces')" for="unit-of-measure-decimalPlaces">Decimal Places</label>
                        <input type="text" class="form-control" name="decimalPlaces" id="unit-of-measure-decimalPlaces"
                            :class="{'valid': !$v.unitOfMeasure.decimalPlaces.$invalid, 'invalid': $v.unitOfMeasure.decimalPlaces.$invalid }" v-model="$v.unitOfMeasure.decimalPlaces.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.unitOfMeasure.extraField')" for="unit-of-measure-extraField">Extra Field</label>
                        <input type="text" class="form-control" name="extraField" id="unit-of-measure-extraField"
                            :class="{'valid': !$v.unitOfMeasure.extraField.$invalid, 'invalid': $v.unitOfMeasure.extraField.$invalid }" v-model="$v.unitOfMeasure.extraField.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.unitOfMeasure.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./unit-of-measure-update.component.ts">
</script>
