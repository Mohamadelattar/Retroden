import { Routes } from '@angular/router';


export const routes: Routes = [
  {
    path: 'certifications',
    loadChildren: () =>
      import('./features/certifications/certifications-module').then(m => m.CertificationsModule)
  },
  {
    path: 'cv',
    loadChildren: () =>
      import('./features/cv/cv-module').then(m => m.CvModule)
  },
  {
    path: 'professionals',
    loadChildren: () =>
      import('./features/professionals/professionals-module').then(m => m.ProfessionalsModule)
  },
  {
    path: 'companies',
    loadChildren: () =>
      import('./features/companies/companies-routing-module').then(m => m.companyRoutes)
  }
  ,
  {
    path: 'skills',
    loadChildren: () =>
      import('./features/skills/skills-module').then(m => m.SkillsModule)
  },
  {
    path: 'jobs',
    loadChildren: () =>
      import('./features/jobs/jobs-module').then(m => m.JobsModule)
  },
  {
    path: 'industries',
    loadChildren: () =>
      import('./features/industries/industries-module').then(m => m.IndustriesModule)
  },
  { path: '', redirectTo: '/professionals', pathMatch: 'full' }
];
