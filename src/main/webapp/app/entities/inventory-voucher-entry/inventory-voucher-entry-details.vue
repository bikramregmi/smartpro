<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="inventoryVoucherEntry">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.inventoryVoucherEntry.detail.title')">InventoryVoucherEntry</span> {{inventoryVoucherEntry.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.srItem')">Sr Item</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucherEntry.srItem}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.srQuantity')">Sr Quantity</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucherEntry.srQuantity}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.srRate')">Sr Rate</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucherEntry.srRate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.srAmount')">Sr Amount</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucherEntry.srAmount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.desItem')">Des Item</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucherEntry.desItem}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.desQuantity')">Des Quantity</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucherEntry.desQuantity}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.desRate')">Des Rate</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucherEntry.desRate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.desAmount')">Des Amount</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucherEntry.desAmount}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.extraField')">Extra Field</span>
                    </dt>
                    <dd>
                        <span>{{inventoryVoucherEntry.extraField}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.inventoryVoucherEntry.inventoryVoucher')">Inventory Voucher</span>
                    </dt>
                    <dd>
                        <div v-if="inventoryVoucherEntry.inventoryVoucherId">
                            <router-link :to="{name: 'InventoryVoucherView', params: {inventoryVoucherId: inventoryVoucherEntry.inventoryVoucherId}}">{{inventoryVoucherEntry.inventoryVoucherId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="inventoryVoucherEntry.id" :to="{name: 'InventoryVoucherEntryEdit', params: {inventoryVoucherEntryId: inventoryVoucherEntry.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./inventory-voucher-entry-details.component.ts">
</script>
