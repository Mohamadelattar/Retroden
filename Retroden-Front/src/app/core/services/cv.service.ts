import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cv } from '../../shared/models/cv.model';

@Injectable({
  providedIn: 'root'
})
export class CvService {
  private baseUrl = 'http://localhost:8080/api/cvs';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Cv[]> {
    return this.http.get<Cv[]>(this.baseUrl);
  }

  getById(id: number): Observable<Cv> {
    return this.http.get<Cv>(`${this.baseUrl}/${id}`);
  }

  create(cv: Cv): Observable<Cv> {
    return this.http.post<Cv>(this.baseUrl, cv);
  }

  update(id: number, cv: Cv): Observable<Cv> {
    return this.http.put<Cv>(`${this.baseUrl}/${id}`, cv);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
