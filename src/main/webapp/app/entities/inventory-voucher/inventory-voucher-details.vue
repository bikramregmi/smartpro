<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="inventoryVoucher">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.inventoryVoucher.detail.title')">InventoryVoucher</span> {{inventoryVoucher.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.srItem')">Sr Item</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.srItem}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.srQuantity')">Sr Quantity</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.srQuantity}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.srRate')">Sr Rate</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.srRate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.srAmount')">Sr Amount</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.srAmount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.narration')">Narration</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.narration}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.desItem')">Des Item</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.desItem}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.desQuantity')">Des Quantity</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.desQuantity}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.desRate')">Des Rate</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.desRate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.desAmount')">Des Amount</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.desAmount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucher.extraField')">Extra Field</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucher.extraField}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="inventoryVoucher.id" :to="{name: 'InventoryVoucherEdit', params: {inventoryVoucherId: inventoryVoucher.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./inventory-voucher-details.component.ts">
</script>
