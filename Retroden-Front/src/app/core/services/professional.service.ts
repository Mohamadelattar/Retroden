import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Professional } from '../../shared/models/professional.model';

@Injectable({ providedIn: 'root' })
export class ProfessionalService {
  private baseUrl = 'http://localhost:8080/professionals';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Professional[]> {
    return this.http.get<Professional[]>(this.baseUrl);
  }

  getById(id: number): Observable<Professional> {
    return this.http.get<Professional>(`${this.baseUrl}/${id}`);
  }

  create(Professional: Professional): Observable<Professional> {
    return this.http.post<Professional>(this.baseUrl, Professional);
  }

  update(id: number, Professional: Professional): Observable<Professional> {
    return this.http.put<Professional>(`${this.baseUrl}/${id}`, Professional);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
