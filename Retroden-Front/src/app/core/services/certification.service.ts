import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Certification } from '../../shared/models/certification.model';

@Injectable({
  providedIn: 'root'
})
export class CertificationService {
  private baseUrl = 'http://localhost:8080/api/certifications';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Certification[]> {
    return this.http.get<Certification[]>(this.baseUrl);
  }

  getById(id: number): Observable<Certification> {
    return this.http.get<Certification>(`${this.baseUrl}/${id}`);
  }

  create(certification: Certification): Observable<Certification> {
    return this.http.post<Certification>(this.baseUrl, certification);
  }

  update(id: number, certification: Certification): Observable<Certification> {
    return this.http.put<Certification>(`${this.baseUrl}/${id}`, certification);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
