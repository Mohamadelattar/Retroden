import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CertificationService } from '../../../core/services/certification.service';
import { Certification } from '../../../shared/models/certification.model';

@Component({
  selector: 'app-certification-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './certification-list.html',
  styleUrls: ['./certification-list.css']
})
export class CertificationList {
  certifications: Certification[] = [];

  constructor(private certificationService: CertificationService) {
    this.loadCertifications();
  }

  loadCertifications(): void {
    this.certificationService.getAll().subscribe({
      next: (data) => (this.certifications = data),
      error: (err) => console.error('Error loading certifications', err)
    });
  }

  deleteCertification(id: number): void {
    if (confirm('Are you sure you want to delete this certification?')) {
      this.certificationService.delete(id).subscribe({
        next: () => {
          this.loadCertifications();
          alert('Certification deleted successfully!');
        },
        error: (err) => console.error('Delete failed', err)
      });
    }
  }
}

