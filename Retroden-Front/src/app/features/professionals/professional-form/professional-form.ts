import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { ProfessionalService } from '../../../core/services/professional.service';
import {Professional} from '../../../shared/models/professional.model';

@Component({
  selector: 'app-professional-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './professional-form.html',
  styleUrls: ['./professional-form.css']
})
export class ProfessionalForm {
  form: FormGroup;
  isEdit = false;

  constructor(
    private fb: FormBuilder,
    private professionalService: ProfessionalService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      id: [null],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      age: [null, [Validators.required, Validators.min(18)]],
      location: ['', Validators.required],
      availability: ['AVAILABLE', Validators.required],
      skills: [''],
      certifications: ['']
    });

    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.isEdit = true;
      this.professionalService.getById(id).subscribe(data => {
        this.form.patchValue({
          ...data,
          skills: data.skills?.join(', '),
          certifications: data.certifications?.join(', ')
        });
      });
    }
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    const formValue = this.form.value;

    const professional: Professional = {
      ...formValue,
      skills: formValue.skills ? formValue.skills.split(',').map((s: string) => s.trim()) : [],
      certifications: formValue.certifications ? formValue.certifications.split(',').map((c: string) => c.trim()) : []
    };

    if (this.isEdit && professional.id) {
      this.professionalService.update(professional.id, professional).subscribe(() => {
        alert('Professional updated successfully!');
        this.router.navigate(['/professionals']);
      });
    } else {
      this.professionalService.create(professional).subscribe(() => {
        alert('Professional created successfully!');
        this.router.navigate(['/professionals']);
      });
    }
  }
}
