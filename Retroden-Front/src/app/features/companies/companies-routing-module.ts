import {NgModule} from '@angular/core';
import {routes} from '../../app.routes';
import {RouterModule, Routes} from '@angular/router';
import { CompanyList } from './company-list/company-list';
import { CompanyForm } from './company-form/company-form';
import { CompanyDetail } from './company-detail/company-detail';


export const companyRoutes: Routes = [
  { path: '', component: CompanyList },
  { path: 'create', component: CompanyForm },
  { path: 'edit/:id', component: CompanyForm },
  { path: ':id', component: CompanyDetail },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompaniesRoutingModule { }
