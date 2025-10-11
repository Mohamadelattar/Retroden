import { Component, signal } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {Certification} from './certification/certification';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, Certification],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Retroden-Front');
}
