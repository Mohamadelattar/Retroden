import { Component } from '@angular/core';
import {Professional} from '../../../shared/models/professional.model';
import {ProfessionalService} from '../../../core/services/professional.service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-professional-list',
  imports: [
    RouterLink
  ],
  templateUrl: './professional-list.html',
  styleUrl: './professional-list.css'
})
export class ProfessionalList {
  professionals: Professional[] = [];

  constructor(private professionalService: ProfessionalService) {
    this.loadProfessionals();
  }

  loadProfessionals(): void {
    this.professionalService.getAll().subscribe({
      next: data => (this.professionals = data),
      error: err => console.error('Failed to load professionals', err)
    });
  }

  deleteProfessionel(id: number): void {
    if (confirm('Are you sure you want to delete this professional?')) {
      this.professionalService.delete(id).subscribe({
        next: () => {
          this.loadProfessionals();
          alert('Professional deleted successfully!');
        },
        error: err => console.error('Delete failed', err)
      });
    }
  }
}
