import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CertificationList} from './certification-list/certification-list';
import {CertificationForm} from './certification-form/certification-form';
import {CertificationDetail} from './certification-detail/certification-detail';


const routes: Routes = [
  { path: '', component: CertificationList },
  { path: 'create', component: CertificationForm },
  { path: 'edit/:id', component: CertificationForm },
  { path: ':id', component: CertificationDetail }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CertificationsRoutingModule {}
