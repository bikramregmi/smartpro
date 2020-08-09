<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="productItem">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.productItem.detail.title')">ProductItem</span> {{productItem.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.productItem.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{productItem.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productItem.group')">Group</span>
                    </dt>
                    <dd>
                        <span>{{productItem.group}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productItem.units')">Units</span>
                    </dt>
                    <dd>
                        <span>{{productItem.units}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productItem.rate')">Rate</span>
                    </dt>
                    <dd>
                        <span>{{productItem.rate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productItem.quantityPerRate')">Quantity Per Rate</span>
                    </dt>
                    <dd>
                        <span>{{productItem.quantityPerRate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productItem.value')">Value</span>
                    </dt>
                    <dd>
                        <span>{{productItem.value}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productItem.extraField')">Extra Field</span>
                    </dt>
                    <dd>
                        <span>{{productItem.extraField}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.productItem.company')">Company</span>
                    </dt>
                    <dd>
                        <div v-if="productItem.companyId">
                            <router-link :to="{name: 'CompanyView', params: {companyId: productItem.companyId}}">{{productItem.companyId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="productItem.id" :to="{name: 'ProductItemEdit', params: {productItemId: productItem.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./product-item-details.component.ts">
</script>
