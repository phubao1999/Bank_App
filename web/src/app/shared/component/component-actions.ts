import { Injectable } from '@angular/core';
declare var $: any;

@Injectable()
export class ComponentActions {

    constructor() { }

    showLoading() {
        $('.overlay').show();
    }

    hideLoading() {
        $('.overlay').hide();
    }
}
