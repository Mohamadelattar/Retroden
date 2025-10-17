import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {CompanyService} from '../../../core/services/company.service';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import {Company} from '../../../shared/models/company.model';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-company-form',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './company-form.html',
  styleUrl: './company-form.css'
})
export class CompanyForm {
  form: FormGroup;
  isEdit = false;

  constructor(
    private fb: FormBuilder,
    private companyService: CompanyService,
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
      this.companyService.getById(id).subscribe({
        next: (data) => this.form.patchValue(data),
        error: (err) => console.error('Error fetching company', err)
      });
    }
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    const company: Company = this.form.value;

    if (this.isEdit) {
      this.companyService.update(company.id!, company).subscribe({
        next: () => {
          alert('Company updated successfully!');
          this.router.navigate(['/companies']);
        },
        error: (err) => console.error('Update failed', err)
      });
    } else {
      this.companyService.create(company).subscribe({
        next: () => {
          alert('Company created successfully!');
          this.router.navigate(['/companies']);
        },
        error: (err) => console.error('Create failed', err)
      });
    }
  }

}
