import { FooterComponent } from './core/footer/footer.component';
import { NavComponent } from './core/nav/nav.component';
import { EditorComponent } from './components/editor/editor.component';
import { SelectDropdownComponent } from './components/select-dropdown/select-dropdown.component';
import { ModelSearchComponent } from './components/model-search/model-search.component';
import { DataTableComponent } from './components/data-table/data-table.component';
import { RouterModule } from '@angular/router';
import { UserLayoutComponent } from './layout/user-layout/user-layout.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminLayoutComponent } from './layout/admin-layout/admin-layout.component';
import { HttpModule } from '@angular/http';
import { ReactiveFormsModule } from '@angular/forms';
import { TranslateModule, TranslateLoader, TranslateService } from '@ngx-translate/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { EditorModule } from '@tinymce/tinymce-angular';
export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    UserLayoutComponent,
    AdminLayoutComponent,
    DataTableComponent,
    ModelSearchComponent,
    SelectDropdownComponent,
    EditorComponent,
    NavComponent,
    FooterComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    }),
    EditorModule
  ],
  exports: [
    UserLayoutComponent,
    AdminLayoutComponent,
    DataTableComponent,
    ModelSearchComponent,
    SelectDropdownComponent,
    EditorComponent,
    NavComponent,
    FooterComponent,
    HttpModule,
    ReactiveFormsModule
  ],
  providers: []
})
export class SharedModule { }
