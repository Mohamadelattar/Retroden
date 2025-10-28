import { Routes } from '@angular/router';
import { SignupComponent } from './features/auth/signup/signup.component';
import { LoginComponent } from './features/auth/login/login.component';
import { AuthLayout } from './layouts/auth-layout/auth-layout.component';
import { MainLayout } from './layouts/main-layout/main-layout.component';

export const routes: Routes = [
  // ===== Auth Layout (Login / Signup) =====
  {
    path: '',
    component: AuthLayout,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent },
      { path: '', redirectTo: 'login', pathMatch: 'full' }
    ]
  },

  // ===== Main Layout (Dashboard after login) =====
  {
    path: '',
    component: MainLayout,
    children: [
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
          import('./features/professionals/professionals-routing-module').then(m => m.ProfessionalsRoutingModule)
      },
      {
        path: 'companies',
        loadChildren: () =>
          import('./features/companies/companies-routing-module').then(m => m.companyRoutes)
      },
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
      }
    ]
  },

  // ===== Fallback =====
  { path: '**', redirectTo: 'login' }
];
