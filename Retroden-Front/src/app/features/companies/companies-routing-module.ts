import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CompanyDetail} from './company-detail/company-detail';
import {CompanyList} from './company-list/company-list';
import {CompanyForm} from './company-form/company-form';
import {routes} from '../../app.routes';

export const companyRoutes: Routes = [
  { path: '', component: CompanyList },           // List all companies
  { path: 'create', component: CompanyForm },     // Create a new company
  { path: 'edit/:id', component: CompanyForm },   // Edit an existing company
  { path: ':id', component: CompanyDetail }       // View company detail
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompaniesRoutingModule { }
