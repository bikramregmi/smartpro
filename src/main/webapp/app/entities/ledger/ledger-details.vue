<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="ledger">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.ledger.detail.title')">Ledger</span> {{ledger.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.ledger.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{ledger.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.ledger.mailingName')">Mailing Name</span>
                    </dt>
                    <dd>
                        <span>{{ledger.mailingName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.ledger.mailingAddress')">Mailing Address</span>
                    </dt>
                    <dd>
                        <span>{{ledger.mailingAddress}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.ledger.panNo')">Pan No</span>
                    </dt>
                    <dd>
                        <span>{{ledger.panNo}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.ledger.extraField1')">Extra Field 1</span>
                    </dt>
                    <dd>
                        <span>{{ledger.extraField1}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.ledger.extraField2')">Extra Field 2</span>
                    </dt>
                    <dd>
                        <span>{{ledger.extraField2}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.ledger.extraField3')">Extra Field 3</span>
                    </dt>
                    <dd>
                        <span>{{ledger.extraField3}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.ledger.extraField4')">Extra Field 4</span>
                    </dt>
                    <dd>
                        <span>{{ledger.extraField4}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.ledger.extraField5')">Extra Field 5</span>
                    </dt>
                    <dd>
                        <span>{{ledger.extraField5}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.ledger.ledger')">Ledger</span>
                    </dt>
                    <dd>
                        <div v-if="ledger.ledgerId">
                            <router-link :to="{name: 'LedgerView', params: {ledgerId: ledger.ledgerId}}">{{ledger.ledgerId}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="ledger.id" :to="{name: 'LedgerEdit', params: {ledgerId: ledger.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./ledger-details.component.ts">
</script>
