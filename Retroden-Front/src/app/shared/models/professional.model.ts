export interface Professional {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  age: number;
  location: string;
  availability: 'AVAILABLE' | 'BUSY' | 'UNAVAILABLE';
  skills?: string[];
  idCv?: number;
  certifications?: string[];
}
