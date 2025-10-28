import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {AuthService} from '../auth.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    this.authService.login(this.form.value).subscribe({
      next: (res: any) => {
        alert('Login successful!');
        // You can store JWT token or user info here
        if (res.type === 'PROFESSIONAL') {
          this.router.navigate(['/professionels']);
        } else {
          this.router.navigate(['/companies']);
        }
      },
      error: err => console.error('Login failed', err)
    });
  }
}
