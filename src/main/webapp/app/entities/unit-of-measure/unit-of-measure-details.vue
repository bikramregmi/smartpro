<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="unitOfMeasure">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.unitOfMeasure.detail.title')">UnitOfMeasure</span> {{unitOfMeasure.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.unitOfMeasure.formalName')">Formal Name</span>
                    </dt>
                    <dd>
                        <span>{{unitOfMeasure.formalName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.unitOfMeasure.type')">Type</span>
                    </dt>
                    <dd>
                        <span>{{unitOfMeasure.type}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.unitOfMeasure.symbol')">Symbol</span>
                    </dt>
                    <dd>
                        <span>{{unitOfMeasure.symbol}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.unitOfMeasure.decimalPlaces')">Decimal Places</span>
                    </dt>
                    <dd>
                        <span>{{unitOfMeasure.decimalPlaces}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.unitOfMeasure.extraField')">Extra Field</span>
                    </dt>
                    <dd>
                        <span>{{unitOfMeasure.extraField}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="unitOfMeasure.id" :to="{name: 'UnitOfMeasureEdit', params: {unitOfMeasureId: unitOfMeasure.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./unit-of-measure-details.component.ts">
</script>
