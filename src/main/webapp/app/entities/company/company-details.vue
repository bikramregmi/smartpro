<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="company">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.company.detail.title')">Company</span> {{company.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.company.companyName')">Company Name</span>
                    </dt>
                    <dd>
                        <span>{{company.companyName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.address')">Address</span>
                    </dt>
                    <dd>
                        <span>{{company.address}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.email')">Email</span>
                    </dt>
                    <dd>
                        <span>{{company.email}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.fy')">Fy</span>
                    </dt>
                    <dd>
                        <span>{{company.fy}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.bookDate')">Book Date</span>
                    </dt>
                    <dd>
                        <span>{{company.bookDate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.currencyString')">Currency String</span>
                    </dt>
                    <dd>
                        <span>{{company.currencyString}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.currencySymbol')">Currency Symbol</span>
                    </dt>
                    <dd>
                        <span>{{company.currencySymbol}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.currencySubString')">Currency Sub String</span>
                    </dt>
                    <dd>
                        <span>{{company.currencySubString}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.dealerType')">Dealer Type</span>
                    </dt>
                    <dd>
                        <span>{{company.dealerType}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.taxRate')">Tax Rate</span>
                    </dt>
                    <dd>
                        <span>{{company.taxRate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.type')">Type</span>
                    </dt>
                    <dd>
                        <span>{{company.type}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.extraField')">Extra Field</span>
                    </dt>
                    <dd>
                        <span>{{company.extraField}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.company.extrafield1')">Extrafield 1</span>
                    </dt>
                    <dd>
                        <span>{{company.extrafield1}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="company.id" :to="{name: 'CompanyEdit', params: {companyId: company.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./company-details.component.ts">
</script>
