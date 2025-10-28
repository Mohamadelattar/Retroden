import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {NavbarComponent} from '../../core/components/navbar/navbar';
import {FooterComponent} from '../../core/components/footer/footer';


@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent, FooterComponent],
  template: `
    <app-navbar></app-navbar>
    <main class="container mt-3">
      <router-outlet></router-outlet>
    </main>
    <app-footer></app-footer>
  `
})
export class MainLayout {}
