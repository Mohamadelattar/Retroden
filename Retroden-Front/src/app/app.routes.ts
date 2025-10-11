import { Routes } from '@angular/router';
import {Certification} from './certification/certification';

export const routes: Routes = [
  {
    path: 'certification',
    component: Certification,
  },
  {
    path: '',
    redirectTo: 'certification',
    pathMatch: 'full'
  }
];
