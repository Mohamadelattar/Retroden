import { Component } from '@angular/core';
import {Professional} from '../../../shared/models/professional.model';
import {ProfessionalService} from '../../../core/services/professional.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-professional-detail',
  imports: [],
  templateUrl: './professional-detail.html',
  styleUrl: './professional-detail.css'
})
export class ProfessionalDetail {
  professional = {
    firstName: 'Sophie',
    lastName: 'Martin',
    email: 'sophie.martin@example.com',
    age: 29,
    location: 'Paris, France',
    availability: 'Available',
    certifications: [
      { id: 1, name: 'AWS Certified Solutions Architect' },
      { id: 2, name: 'Google Data Analytics Professional Certificate' },
      { id: 3, name: 'Scrum Master (PSM I)' }
    ],
    skills: ['Java', 'Angular', 'Spring Boot', 'Docker', 'SQL']
  };

  constructor(
    private professionalService: ProfessionalService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id')) || 1; // for now test with 1

  }
}
