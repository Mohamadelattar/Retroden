import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CertificationService } from '../../../core/services/certification.service';
import { Certification } from '../../../shared/models/certification.model';

@Component({
  selector: 'app-certification-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './certification-list.html'
})
export class CertificationList {
  certifications: Certification[] = [];

  constructor(private certificationService: CertificationService) {
    this.loadCertifications();
  }

  loadCertifications(): void {
    this.certificationService.getAll().subscribe(data => {
      this.certifications = data;
    });
  }
}
