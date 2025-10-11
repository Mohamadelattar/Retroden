import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Professional } from '../../shared/models/professional.model';

@Injectable({
  providedIn: 'root'
})
export class ProfessionalService {
  private baseUrl = 'http://localhost:3000/api/professionals';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Professional[]> {
    return this.http.get<Professional[]>(this.baseUrl);
  }

  getById(id: number): Observable<Professional> {
    return this.http.get<Professional>(`${this.baseUrl}/${id}`);
  }

  create(professional: Professional): Observable<Professional> {
    return this.http.post<Professional>(this.baseUrl, professional);
  }

  update(id: number, professional: Professional): Observable<Professional> {
    return this.http.put<Professional>(`${this.baseUrl}/${id}`, professional);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
