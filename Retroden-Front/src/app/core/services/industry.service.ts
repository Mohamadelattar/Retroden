import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Industry } from '../../shared/models/industry.model';

@Injectable({
  providedIn: 'root'
})
export class IndustryService {
  private baseUrl = 'http://localhost:3000/api/industries';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Industry[]> {
    return this.http.get<Industry[]>(this.baseUrl);
  }

  getById(id: number): Observable<Industry> {
    return this.http.get<Industry>(`${this.baseUrl}/${id}`);
  }

  create(industry: Industry): Observable<Industry> {
    return this.http.post<Industry>(this.baseUrl, industry);
  }

  update(id: number, industry: Industry): Observable<Industry> {
    return this.http.put<Industry>(`${this.baseUrl}/${id}`, industry);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
