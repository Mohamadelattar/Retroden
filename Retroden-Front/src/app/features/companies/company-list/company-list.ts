import { Component } from '@angular/core';
import {Company} from '../../../shared/models/company.model';
import {CompanyService} from '../../../core/services/company.service';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

@Component({
  selector: 'app-company-list',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './company-list.html',
  styleUrl: './company-list.css'
})
export class CompanyList {
  companies: Company[] = [];

  constructor(private companyService: CompanyService) {
    this.loadCompanies();
  }

  loadCompanies(): void {
    this.companyService.getAll().subscribe({
      next: data => (this.companies = data),
      error: err => console.error('Error loading companies', err)
    });
  }

  deleteCompany(id: number): void {
    if (!id) return;
    if (confirm('Are you sure you want to delete this company?')) {
      this.companyService.delete(id).subscribe(() => this.loadCompanies());
    }
  }
}
