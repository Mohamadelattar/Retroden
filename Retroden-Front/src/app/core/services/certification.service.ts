import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Certification } from '../../shared/models/certification.model';

@Injectable({
  providedIn: 'root'
})
export class CertificationService {
  private baseUrl = 'http://localhost:8080/certification';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Certification[]> {
    return this.http.get<Certification[]>(this.baseUrl);
  }

  getById(id: number): Observable<Certification> {
    return this.http.get<Certification>(`${this.baseUrl}/${id}`);
  }

  create(certification: Certification): Observable<string> {
    return this.http.post(this.baseUrl, certification, { responseType: 'text' });
  }

  update(certification: Certification): Observable<Certification> {
    return this.http.put<Certification>(this.baseUrl, certification);
  }

  delete(id: number): Observable<void> {
    const params = new HttpParams().set('id', id);
    return this.http.delete<void>(this.baseUrl, { params });
  }
}
