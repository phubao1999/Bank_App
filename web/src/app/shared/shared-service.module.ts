import { NgModule, Optional, SkipSelf } from '@angular/core';
import { BaseService } from './service/base-service.service';
import { ValidateService } from './service/validate.service';



@NgModule({
  providers: [
    BaseService,
    ValidateService
  ]
})
export class SharedServiceModule {
  constructor(@Optional() @SkipSelf() parentModule: SharedServiceModule) {
    if (parentModule) {
      throw new Error('SharedServicesModule is already loaded. Import it in the AppModule only');
    }
  }
}
