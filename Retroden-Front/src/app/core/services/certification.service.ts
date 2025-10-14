import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Certification } from '../../shared/models/certification.model';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CertificationService {
  private apiUrl = `${environment.apiUrl}/certification`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Certification[]> {
    return this.http.get<Certification[]>(this.apiUrl);
  }

  getById(id: number): Observable<Certification> {
    return this.http.get<Certification>(`${this.apiUrl}/${id}`);
  }

  create(certification: Certification): Observable<string> {
    return this.http.post(this.apiUrl, certification, { responseType: 'text' });
  }

  update(certification: Certification): Observable<Certification> {
    return this.http.put<Certification>(this.apiUrl, certification);
  }

  delete(id: number): Observable<void> {
    const params = new HttpParams().set('id', id);
    return this.http.delete<void>(this.apiUrl, { params });
  }
}
