import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CertificationService } from '../../../core/services/certification.service';
import { Certification } from '../../../shared/models/certification.model';

@Component({
  selector: 'app-certification-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule], // ✅ important
  templateUrl: './certification-form.html',
  styleUrls: ['./certification-form.css']
})
export class CertificationForm {
  form: FormGroup;
  isEdit = false;

  constructor(
    private fb: FormBuilder,
    private certificationService: CertificationService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      id: [null],
      name: ['', Validators.required]
    });

    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.isEdit = true;
      this.certificationService.getById(id).subscribe(cert => this.form.patchValue(cert));
    }
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    const certification: Certification = this.form.value;

    if (this.isEdit) {
      this.certificationService.update(certification).subscribe(() => {
        alert('Certification updated successfully');
        this.router.navigate(['/certifications']);
      });
    } else {
      this.certificationService.create(certification).subscribe(() => {
        alert('Certification created successfully');
        this.router.navigate(['/certifications']);
      });
    }
  }
}
