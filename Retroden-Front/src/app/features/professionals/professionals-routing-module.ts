import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProfessionalList} from './professional-list/professional-list';
import {ProfessionalForm} from './professional-form/professional-form';
import {ProfessionalDetail} from './professional-detail/professional-detail';


const routes: Routes = [
  { path: '', component: ProfessionalList },
  { path: 'create', component: ProfessionalForm },
  { path: 'edit/:id', component: ProfessionalForm },
  { path: ':id', component: ProfessionalDetail }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class ProfessionalsRoutingModule { }
