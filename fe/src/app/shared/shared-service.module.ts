import { PagerService } from './services/helpers/pager.service';
import { TimeService } from './services/helpers/time.service';
import { ValidateService } from './services/helpers/validate.service';
import { NgModule, Optional, SkipSelf } from '@angular/core';
import { BaseService } from './services/helpers/base.service';

@NgModule({
    providers: [
        BaseService,
        ValidateService,
        TimeService,
        PagerService
    ]
})
export class SharedServicesModule {
    constructor(@Optional() @SkipSelf() parentModule: SharedServicesModule) {
        if (parentModule) {
            throw new Error('SharedServicesModule is already loaded. Import it in the AppModule only');
        }
    }
}
